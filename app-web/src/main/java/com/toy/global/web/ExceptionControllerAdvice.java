package com.toy.global.web;

import com.toy.apps.member.exception.MemberException;
import com.toy.apps.order.exception.OrderException;
import com.toy.apps.product.exception.ProductException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class ExceptionControllerAdvice {

    private void printExceptionHandlerMessage(Exception e) {
        log.info(this.getClass() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + "\n\t"
                 + e.getClass() + ": " + e.getMessage() + "(" + e.getStackTrace()[0].toString() + ")");
    }

    @ExceptionHandler(OrderException.class)
    protected ResponseEntity<ApiResponseBody<?>> handleException(OrderException e) {
        printExceptionHandlerMessage(e);

        return new ResponseEntity<>(ApiResponseBody.error(e.getMessage()), e.getHttpStatus());
    }

    @ExceptionHandler(MemberException.class)
    protected ResponseEntity<ApiResponseBody<?>> handleException(MemberException e) {
        printExceptionHandlerMessage(e);

        return new ResponseEntity<>(ApiResponseBody.error(e.getMessage()), e.getHttpStatus());
    }

    @ExceptionHandler(ProductException.class)
    protected ResponseEntity<ApiResponseBody<?>> handleException(ProductException e) {
        printExceptionHandlerMessage(e);

        return new ResponseEntity<>(ApiResponseBody.error(e.getMessage()), e.getHttpStatus());
    }

    /**
     * 목적 : 유효성 검사/체크 Exception 핸들링
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ApiResponseBody<?>> handleException(MethodArgumentNotValidException e) {
        printExceptionHandlerMessage(e);

        String message = e.getBindingResult()
                          .getAllErrors()
                          .stream()
                          .map(DefaultMessageSourceResolvable::getDefaultMessage)
                          .collect(Collectors.joining(" | "));
        return new ResponseEntity<>(ApiResponseBody.error(message), HttpStatus.BAD_REQUEST);
    }

    /**
     * 목적 : (위의 설정된 예외 외의) Exception 핸들링
     */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ApiResponseBody<?>> handleException(Exception e) {
        printExceptionHandlerMessage(e);
        log.error(e.getMessage());

        return new ResponseEntity<>(ApiResponseBody.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
