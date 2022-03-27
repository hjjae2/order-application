package com.toy.global.security;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;

public class ToyAccessDeniedException extends AccessDeniedException {

    public ToyAccessDeniedException() {
        super("");
    }

    public ToyAccessDeniedException(String msg) {
        super(msg);
    }

    public ToyAccessDeniedException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
