/*
 * CheckableOptionView.java
 *
 * Copyright (c) 2016 Auth0 (http://auth0.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.auth0.android.lock.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.auth0.android.lock.R;
import com.auth0.android.lock.enums.PasswordStrength;

import java.util.regex.Pattern;


public class PasswordStrengthView extends LinearLayout {

    private static final String TAG = PasswordStrengthView.class.getSimpleName();
    private static final int MAX_IDENTICAL_CHARACTERS_IN_A_ROW = 2;
    private static final int MAX_LENGTH = 128;

    private final Pattern patternUppercase = Pattern.compile("^.*[A-Z]+.*$");
    private final Pattern patternLowercase = Pattern.compile("^.*[a-z]+.*$");
    private final Pattern patternSpecial = Pattern.compile("^.*[ !\"#\\$%&'\\(\\)\\*\\+,-\\./:;<=>\\?@\\[\\\\\\]\\^_`{\\|}~]+.*$");
    private final Pattern patternNumeric = Pattern.compile("^.*[0-9]+.*$");

    @PasswordStrength
    private int strength;

    private TextView titleMustHave;
    private TextView titleAtLeast;
    private CheckableOptionView optionLength;
    private CheckableOptionView optionIdenticalCharacters;
    private CheckableOptionView optionLowercase;
    private CheckableOptionView optionUppercase;
    private CheckableOptionView optionNumbers;
    private CheckableOptionView optionSpecialCharacters;

    public PasswordStrengthView(Context context) {
        super(context);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.com_auth0_lock_password_strength, this);
        titleMustHave = (TextView) findViewById(R.id.com_auth0_lock_password_strength_title_must_have);
        titleAtLeast = (TextView) findViewById(R.id.com_auth0_lock_password_strength_title_at_least);

        optionLength = (CheckableOptionView) findViewById(R.id.com_auth0_lock_password_strength_option_length);
        optionLength.setMandatory(true);
        optionIdenticalCharacters = (CheckableOptionView) findViewById(R.id.com_auth0_lock_password_strength_option_identical_characters);
        optionIdenticalCharacters.setMandatory(true);
        optionLowercase = (CheckableOptionView) findViewById(R.id.com_auth0_lock_password_strength_option_lowercase);
        optionUppercase = (CheckableOptionView) findViewById(R.id.com_auth0_lock_password_strength_option_uppercase);
        optionNumbers = (CheckableOptionView) findViewById(R.id.com_auth0_lock_password_strength_option_numbers);
        optionSpecialCharacters = (CheckableOptionView) findViewById(R.id.com_auth0_lock_password_strength_option_special_characters);
        setStrength(PasswordStrength.NONE);
    }

    /**
     * @see "https://auth0.com/docs/connections/database/password-strength"
     */
    private void showPolicy() {
        if (strength == PasswordStrength.NONE) {
            setVisibility(GONE);
            return;
        }
        setVisibility(VISIBLE);

        optionLowercase.setMandatory(strength == PasswordStrength.FAIR);
        optionUppercase.setMandatory(strength == PasswordStrength.FAIR);
        optionNumbers.setMandatory(strength == PasswordStrength.FAIR);


        optionSpecialCharacters.setVisibility(strength == PasswordStrength.EXCELLENT || strength == PasswordStrength.GOOD ? VISIBLE : GONE);
        optionSpecialCharacters.setVisibility(strength == PasswordStrength.EXCELLENT ? VISIBLE : GONE);

        switch (strength) {
            case PasswordStrength.EXCELLENT:
                break;
            case PasswordStrength.GOOD:
                break;
            case PasswordStrength.FAIR:
                break;
            case PasswordStrength.LOW:
                break;
            case PasswordStrength.NONE:

                break;
        }
    }

    private boolean hasIdenticalCharacters(@NonNull String input) {
        int count = 0;
        char lastChar = 0;
        for (char c : input.toCharArray()) {
            if (lastChar == c) {
                count++;
            } else {
                lastChar = c;
                count = 1;
            }

            if (count > MAX_IDENTICAL_CHARACTERS_IN_A_ROW) {
                optionIdenticalCharacters.setChecked(false);
                return true;
            }
        }
        optionIdenticalCharacters.setChecked(true);
        return false;
    }

    private boolean hasUppercaseCharacters(@NonNull String input) {
        boolean v = patternUppercase.matcher(input).matches();
        optionUppercase.setChecked(v);
        return v;
    }

    private boolean hasLowercaseCharacters(@NonNull String input) {
        boolean v = patternLowercase.matcher(input).matches();
        optionLowercase.setChecked(v);
        return v;
    }

    private boolean hasNumericCharacters(@NonNull String input) {
        boolean v = patternNumeric.matcher(input).matches();
        optionNumbers.setChecked(v);
        return v;
    }

    private boolean hasSpecialCharacters(@NonNull String input) {
        boolean v = patternSpecial.matcher(input).matches();
        optionSpecialCharacters.setChecked(v);
        return v;
    }

    private boolean hasMinimumLength(@NonNull String input, int length) {
        boolean v = input.length() >= length && input.length() <= MAX_LENGTH;
        optionLength.setChecked(v);
        return v;
    }

    private boolean atLeastThree(boolean a, boolean b, boolean c, boolean d) {
        boolean one = a && b && (c ^ d);
        boolean two = b && c && (d ^ a);
        boolean three = c && d && (a ^ b);

        return one || two || three;
    }

    /**
     * Sets the current level of Strength that this widget is going to validate.
     *
     * @param strength the required strength level.
     */
    public void setStrength(@PasswordStrength int strength) {
        this.strength = strength;
        showPolicy();
    }

    /**
     * Checks that all the requirements are meet.
     *
     * @param password the current password to validate
     * @return whether the given password complies with this password policy or not.
     */
    public boolean isValid(String password) {
        if (password == null) {
            return false;
        }

        boolean length = true;
        boolean other = true;
        switch (strength) {
            case PasswordStrength.EXCELLENT:
                boolean atLeast = atLeastThree(hasLowercaseCharacters(password), hasUppercaseCharacters(password), hasNumericCharacters(password), hasSpecialCharacters(password));
                other = hasIdenticalCharacters(password) && atLeast;
                length = hasMinimumLength(password, 10);
                break;
            case PasswordStrength.GOOD:
                other = atLeastThree(hasLowercaseCharacters(password), hasUppercaseCharacters(password), hasNumericCharacters(password), hasSpecialCharacters(password));
                length = hasMinimumLength(password, 8);
                break;
            case PasswordStrength.FAIR:
                other = hasLowercaseCharacters(password) && hasUppercaseCharacters(password) && hasNumericCharacters(password);
                length = hasMinimumLength(password, 8);
                break;
            case PasswordStrength.LOW:
                length = hasMinimumLength(password, 6);
                break;
            case PasswordStrength.NONE:
                length = hasMinimumLength(password, 1);
                break;
        }
        return length && other;
    }

}
