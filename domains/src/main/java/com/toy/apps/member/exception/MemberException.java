package com.toy.apps.member.exception;

import org.springframework.http.HttpStatus;

public class MemberException extends RuntimeException {

    public MemberException() {
        this("사용자에 대한 오류가 발생하였습니다.");
    }

    public MemberException(String message) {
        super(message);
    }

    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
