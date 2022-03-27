package com.toy.global.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class ToyAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final static String APPLICATION_JSON_UTF8_VALUE = "application/json;charset=UTF-8";

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        Map<String, Object> errorResponse = new LinkedHashMap<>() {{
            put("result", false);
            put("message", "로그인이 필요한 기능입니다.");
            put("data", null);
        }};

        ObjectMapper objectMapper = new ObjectMapper();
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(APPLICATION_JSON_UTF8_VALUE);
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}
