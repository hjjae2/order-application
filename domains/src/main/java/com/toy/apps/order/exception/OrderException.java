package com.toy.apps.order.exception;

import org.springframework.http.HttpStatus;

public class OrderException extends RuntimeException {

    public OrderException() {
        this("주문에 대한 오류가 발생했습니다.");
    }

    public OrderException(String message) {
        super(message);
    }

    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
