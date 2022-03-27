package com.toy.apps.order.exception;

import org.springframework.http.HttpStatus;

public class OrderNotFoundException extends OrderException {

    public OrderNotFoundException() {
        this("주문 정보를 찾을 수 없습니다.");
    }

    public OrderNotFoundException(String message) {
        super(message);
    }

    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
