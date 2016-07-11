/*
 * MockBaseCallback.java
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

package com.auth0.android.lock.utils;

import com.auth0.android.auth0.lib.Auth0Exception;
import com.auth0.android.auth0.lib.callback.BaseCallback;

import java.util.concurrent.Callable;

public class MockBaseCallback<T> implements BaseCallback<T> {

    private T payload;
    private Auth0Exception error;

    @Override
    public void onSuccess(T payload) {
        this.payload = payload;
    }

    @Override
    public void onFailure(Auth0Exception error) {
        this.error = error;
    }

    public Callable<T> payload() {
        return new Callable<T>() {
            @Override
            public T call() throws Exception {
                return payload;
            }
        };
    }

    public Callable<Auth0Exception> error() {
        return new Callable<Auth0Exception>() {
            @Override
            public Auth0Exception call() throws Exception {
                return error;
            }
        };
    }

    public Auth0Exception getError() {
        return error;
    }
}
