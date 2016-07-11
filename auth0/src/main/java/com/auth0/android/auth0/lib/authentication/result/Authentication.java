/*
 * Authentication.java
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

package com.auth0.android.auth0.lib.authentication.result;


import static com.auth0.android.auth0.lib.util.CheckHelper.checkArgument;

/**
 * The result of a successful authentication against Auth0
 * Contains the logged in user's {@link com.auth0.android.auth0.lib.authentication.result.Credentials} and {@link com.auth0.android.auth0.lib.authentication.result.UserProfile}.
 * @see com.auth0.authentication.AuthenticationAPIClient#getProfileAfter(AuthenticationRequest) 
 */
public class Authentication {

    private final com.auth0.android.auth0.lib.authentication.result.UserProfile profile;
    private final com.auth0.android.auth0.lib.authentication.result.Credentials credentials;

    public Authentication(com.auth0.android.auth0.lib.authentication.result.UserProfile profile, com.auth0.android.auth0.lib.authentication.result.Credentials credentials) {
        checkArgument(profile != null, "profile must be non-null");
        checkArgument(credentials != null, "credentials must be non-null");
        this.profile = profile;
        this.credentials = credentials;
    }

    public com.auth0.android.auth0.lib.authentication.result.UserProfile getProfile() {
        return profile;
    }

    public Credentials getCredentials() {
        return credentials;
    }
}
