package com.toy.apps.member.exception;

import org.springframework.http.HttpStatus;

public class MemberFailedLoginException extends MemberException {

    public MemberFailedLoginException() {
        this("로그인에 실패하였습니다.");
    }

    public MemberFailedLoginException(String message) {
        super(message);
    }

    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
