package com.zyj.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Util {
    public static void addCookie(HttpServletResponse resp, String key, String value, int hours) {
        Cookie cookie = new Cookie(key, value);
        cookie.setPath("/");
        cookie.setMaxAge(hours * 60 * 60);
        resp.addCookie(cookie);
    }

    public static String getCookie(HttpServletRequest req, String key) {
        Cookie[] cookies = req.getCookies();
        if (cookies == null) return null;
        String value = "";
        for (Cookie cookie : cookies) {
            if (key.equals(cookie.getName())) {
                value = cookie.getValue();
            }
        }
        return value;
    }

    public static void delCookie(HttpServletRequest req, String key) {
        Cookie[] cookies = req.getCookies();
        if (cookies == null) return;
        for (Cookie cookie : cookies) {
            if (key.equals(cookie.getName())) {
                cookie.setMaxAge(0);
            }
        }
    }
}
