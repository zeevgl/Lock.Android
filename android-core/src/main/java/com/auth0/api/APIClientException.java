/*
 * APIClientException.java
 *
 * Copyright (c) 2014 Auth0 (http://auth0.com)
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

package com.auth0.api;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hernan on 12/10/14.
 */
public class APIClientException extends RuntimeException {

    private int statusCode;

    private Map responseError;

    public APIClientException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
        this.statusCode = -1;
        this.responseError = new HashMap();
    }

    public APIClientException(String detailMessage, int statusCode, Map responseError) {
        super(detailMessage);
        this.statusCode = statusCode;
        this.responseError = responseError != null ? responseError : new HashMap();
    }

    public APIClientException(String detailMessage, Throwable throwable, int statusCode, Map responseError) {
        super(detailMessage, throwable);
        this.statusCode = statusCode;
        this.responseError = responseError != null ? responseError : new HashMap();
    }

    public Map getResponseError() {
        return responseError;
    }

}
