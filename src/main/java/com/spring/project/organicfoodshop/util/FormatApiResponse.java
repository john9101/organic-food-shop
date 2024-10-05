package com.spring.project.organicfoodshop.util;

import com.spring.project.organicfoodshop.domain.response.api.ApiSuccessResponse;
import com.spring.project.organicfoodshop.util.annotation.ApiRequestMessage;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class FormatApiResponse implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(
            Object body,
            MethodParameter returnType,
            MediaType selectedContentType,
            Class selectedConverterType,
            ServerHttpRequest request,
            ServerHttpResponse response) {
        HttpServletResponse httpServletResponse = ((ServletServerHttpResponse) response).getServletResponse();
        int status = httpServletResponse.getStatus();
        if (status >= HttpStatus.BAD_REQUEST.value() || body instanceof String) {
            return body;
        }

        ApiRequestMessage apiRequestMessage = returnType.getMethodAnnotation(ApiRequestMessage.class);
        return ApiSuccessResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(status)
                .data(body)
                .message(apiRequestMessage != null ? apiRequestMessage.value() + " success" : "Call API success")
                .build();
    }
}
