package com.spring.project.organicfoodshop.domain.response.api;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ApiSuccessResponse<T> {
    private LocalDateTime timestamp;
    private int status;
    private T data;
    private Object message;
}
