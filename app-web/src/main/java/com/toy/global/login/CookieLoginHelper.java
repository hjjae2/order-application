package com.toy.global.login;

import lombok.experimental.UtilityClass;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

import static com.toy.global.login.CookieLoginService.COOKIE_DOMAIN;
import static com.toy.global.login.CookieLoginService.COOKIE_HTTPONLY;
import static com.toy.global.login.CookieLoginService.COOKIE_MAX_AGE;
import static com.toy.global.login.CookieLoginService.COOKIE_NAME;
import static com.toy.global.login.CookieLoginService.COOKIE_PATH;
import static com.toy.global.login.CookieLoginService.COOKIE_SECURE;

@UtilityClass
public class CookieLoginHelper {

    public Cookie fromBy(HttpServletRequest request) throws MissingRequestCookieException {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            throw new MissingRequestCookieException();
        }

        return Arrays.stream(cookies)
                     .filter((cookie) -> COOKIE_NAME.equals(cookie.getName()))
                     .findFirst()
                     .orElseThrow(MissingRequestCookieException::new);
    }

    public Cookie generateLoginCookie(final String value) {
        Cookie cookie = new Cookie(COOKIE_NAME, value);
        cookie.setPath(COOKIE_PATH);
        cookie.setMaxAge(COOKIE_MAX_AGE);
        cookie.setHttpOnly(COOKIE_HTTPONLY);
        cookie.setSecure(COOKIE_SECURE);
        cookie.setDomain(COOKIE_DOMAIN);
        return cookie;
    }

    public Cookie generateLogoutCookie() {
        Cookie cookie = new Cookie(COOKIE_NAME, null);
        cookie.setPath(COOKIE_PATH);
        cookie.setMaxAge(0);
        cookie.setHttpOnly(COOKIE_HTTPONLY);
        cookie.setSecure(COOKIE_SECURE);
        cookie.setDomain(COOKIE_DOMAIN);
        return cookie;
    }
}
