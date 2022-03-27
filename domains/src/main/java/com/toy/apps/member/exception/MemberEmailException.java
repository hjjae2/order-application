package com.toy.apps.member.exception;

import org.springframework.http.HttpStatus;

public class MemberEmailException extends MemberException {

    public MemberEmailException() {
        this("해당 이메일은 사용할 수 없습니다.");
    }

    public MemberEmailException(String message) {
        super(message);
    }

    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
