package com.toy.global.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;

@Getter
@Builder
@AllArgsConstructor
public class ApiResponseBody<T> {
    private boolean result;
    private String message;
    private T data;

    public static ApiResponseBody<?> error(String message) {
        return ApiResponseBody.builder()
                              .result(false)
                              .message(message)
                              .data(new ArrayList<>())
                              .build();
    }

    public static ApiResponseBody<?> success(Object data) {
        return ApiResponseBody.builder()
                              .result(true)
                              .message(null)
                              .data(data)
                              .build();
    }
}
