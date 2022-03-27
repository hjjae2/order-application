package com.toy.apps.member.exception;

import org.springframework.http.HttpStatus;

public class MemberValidationException extends MemberException {

    public MemberValidationException() {
        super("유효하지 않은 사용자입니다.");
    }

    public MemberValidationException(String message) {
        super(message);
    }

    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
