package com.spring.project.organicfoodshop.controller.advice;

import com.spring.project.organicfoodshop.domain.response.api.ApiSuccessResponse;
import com.spring.project.organicfoodshop.util.annotation.ApiRequestMessage;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
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
public class ApiResponseBodyAdvice implements ResponseBodyAdvice {
    @Override
    public boolean supports(@NotNull MethodParameter returnType, @NotNull Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(
            Object body,
            @NotNull MethodParameter returnType,
            @NotNull MediaType selectedContentType,
            @NotNull Class selectedConverterType,
            @NotNull ServerHttpRequest request,
            @NotNull ServerHttpResponse response) {
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
                .message(apiRequestMessage != null ? apiRequestMessage.value() + " success" : "Call API request success")
                .build();
    }
}
