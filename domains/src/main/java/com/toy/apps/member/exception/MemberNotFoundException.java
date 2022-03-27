package com.toy.apps.member.exception;

import org.springframework.http.HttpStatus;

public class MemberNotFoundException extends MemberException {

    public MemberNotFoundException() {
        this("해당 사용자를 찾을 수 없습니다.");
    }

    public MemberNotFoundException(String message) {
        super(message);
    }

    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
