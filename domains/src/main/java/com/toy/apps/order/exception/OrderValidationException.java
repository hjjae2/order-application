package com.toy.apps.order.exception;

import org.springframework.http.HttpStatus;

public class OrderValidationException extends OrderException {

    public OrderValidationException() {
        this("유효하지 않은 주문입니다.");
    }

    public OrderValidationException(String message) {
        super(message);
    }

    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
