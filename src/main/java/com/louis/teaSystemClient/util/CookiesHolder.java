package com.louis.teaSystemClient.util;

import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.BasicCookieStore;

/**
 * ÀµÐ¡ D
 * www.louis.com
 */
public class CookiesHolder {
    private static CookieStore cookieStore = new BasicCookieStore();

    public static synchronized CookieStore getCookieStore() {
        return cookieStore;
    }
}
