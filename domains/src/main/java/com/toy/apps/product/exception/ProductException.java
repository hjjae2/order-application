package com.toy.apps.product.exception;

import org.springframework.http.HttpStatus;

public class ProductException extends RuntimeException {

    public ProductException() {
        this("상품에 대한 오류가 발생했습니다.");
    }

    public ProductException(String message) {
        super(message);
    }

    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
