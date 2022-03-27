package com.toy.apps.product.exception;

import org.springframework.http.HttpStatus;

public class ProductValidationException extends ProductException {

    public ProductValidationException() {
        this("유효하지 않은 상품입니다.");
    }

    public ProductValidationException(String message) {
        super(message);
    }

    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
