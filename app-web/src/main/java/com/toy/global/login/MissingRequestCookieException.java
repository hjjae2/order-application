package com.toy.global.login;

import org.springframework.http.HttpStatus;

public class MissingRequestCookieException extends RuntimeException {

    public MissingRequestCookieException() {
        this("쿠키가 존재하지 않습니다.");
    }

    public MissingRequestCookieException(String message) {
        super(message);
    }

    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
