package com.toy.apps.product.exception;

import org.springframework.http.HttpStatus;

public class ProductNotFoundException extends ProductException {

    public ProductNotFoundException() {
        this("해당 상품을 찾을 수 없습니다.");
    }

    public ProductNotFoundException(Long productId) {
        this(productId + " 번 상품을 찾을 수 없습니다.");
    }

    public ProductNotFoundException(String message) {
        super(message);
    }

    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
