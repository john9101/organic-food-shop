package com.spring.project.organicfoodshop.domain.response.api;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ApiFailureResponse {
    private LocalDateTime timestamp;
    private int status;
    private Object error;
    private String message;
    private String type;
    private String path;

    @Getter
    @Setter
    @Builder
    public static class ValidationError{
        private String field;
        private String detail;
    }
}
