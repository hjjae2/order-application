package com.toy.global.login;

import javax.servlet.http.Cookie;

public interface PasswordBasedCookieLoginService extends CookieLoginService {

    @Override
    default Cookie login(String id) {
        throw new RuntimeException("지원하지 않는 기능입니다.");
    }

    @Override
    Cookie login(String username, String password);
}
