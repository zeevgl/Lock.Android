/*
 * Auth0Parcelable.java
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

package com.auth0.android.lock;

import android.os.Parcel;
import android.os.Parcelable;

import com.auth0.android.auth0.Auth0;


/**
 * This class wraps a {@link Auth0} to make it Parcelable
 */
public class Auth0Parcelable implements Parcelable {

    Auth0 auth0;

    public Auth0Parcelable(Auth0 auth0) {
        this.auth0 = auth0;
    }

    public Auth0 getAuth0() {
        return auth0;
    }

    // PARCELABLE
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(auth0.getClientId());
        dest.writeString(auth0.getDomainUrl());
        dest.writeString(auth0.getConfigurationUrl());
    }

    public static final Parcelable.Creator<Auth0Parcelable> CREATOR
            = new Parcelable.Creator<Auth0Parcelable>() {
        public Auth0Parcelable createFromParcel(Parcel in) {
            return new Auth0Parcelable(in);
        }

        public Auth0Parcelable[] newArray(int size) {
            return new Auth0Parcelable[size];
        }
    };

    private Auth0Parcelable(Parcel in) {
        String clientId = in.readString();
        String domain = in.readString();
        String configurationDomain = in.readString();
        this.auth0 = new Auth0(clientId, domain, configurationDomain);
    }
}
