package com.toy.global.login;

import javax.servlet.http.Cookie;

/**
 * 쿠키 기반 로그인(로그아웃) 서비스 인터페이스
 * */
public interface CookieLoginService extends LoginService<Cookie> {

    String COOKIE_NAME = "toy-session";
    String COOKIE_DOMAIN = "localhost";
    String COOKIE_PATH = "/";
    int COOKIE_MAX_AGE = 60 * 60;
    boolean COOKIE_HTTPONLY = true;
    boolean COOKIE_SECURE = false;

    /**
     * Logout method
     * @return Logout cookie (Expiration is 0)
     * */
    Cookie logout(String username);
}
