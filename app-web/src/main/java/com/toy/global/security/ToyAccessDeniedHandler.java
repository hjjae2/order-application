package com.toy.global.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class ToyAccessDeniedHandler implements AccessDeniedHandler {

    private final static String APPLICATION_JSON_UTF8_VALUE = "application/json;charset=UTF-8";

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        accessDeniedException.printStackTrace();

        Map<String, Object> errorResponse = new LinkedHashMap<>() {{
            put("result", false);
            put("message", "허용되지 않은 요청입니다.");
            put("data", null);
        }};

        ObjectMapper objectMapper = new ObjectMapper();
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType(APPLICATION_JSON_UTF8_VALUE);
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}
