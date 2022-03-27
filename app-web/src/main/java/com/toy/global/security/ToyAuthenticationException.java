package com.toy.global.security;

import org.springframework.security.core.AuthenticationException;

public class ToyAuthenticationException extends AuthenticationException {

    public ToyAuthenticationException() {
        this("");
    }

    public ToyAuthenticationException(String msg) {
        super(msg);
    }

    public ToyAuthenticationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
