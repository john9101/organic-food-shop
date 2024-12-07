package com.spring.project.organicfoodshop.util.exception;
import com.spring.project.organicfoodshop.domain.response.api.ApiFailureResponse;
import com.spring.project.organicfoodshop.util.annotation.ApiRequestMessage;
import com.spring.project.organicfoodshop.util.constant.ErrorTypeEnum;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.LocalDateTime;
import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({UsernameNotFoundException.class,BadCredentialsException.class, UserNotActivatedException.class})
    public ResponseEntity<ApiFailureResponse> handleAuthError(Exception exception, HttpServletRequest request) {
        ApiFailureResponse.ValidationError error = ApiFailureResponse.ValidationError.builder().detail(exception.getMessage()).build();
        if (exception instanceof UsernameNotFoundException) {
            error.setField("email");
        } else if (exception instanceof BadCredentialsException) {
            error.setField("password");
        }else if (exception instanceof UserNotActivatedException) {
            error.setField("activated");
        }

        ApiFailureResponse apiFailureResponse = ApiFailureResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.UNAUTHORIZED.value())
                .error(Collections.singletonList(error))
                .message(formatFailedMessageFromRequest(request))
                .type(ErrorTypeEnum.UNAUTHORIZED.name())
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiFailureResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiFailureResponse> handleValidationError(
            MethodArgumentNotValidException methodArgumentNotValidException,
            HttpServletRequest request) {
        BindingResult bindingResult = methodArgumentNotValidException.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        List<ApiFailureResponse.ValidationError> error = fieldErrors.stream()
                .map(fieldError -> ApiFailureResponse.ValidationError.builder()
                        .field(fieldError.getField())
                        .detail(fieldError.getDefaultMessage())
                        .build()).toList();

        ApiFailureResponse apiFailureResponse = ApiFailureResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(methodArgumentNotValidException.getStatusCode().value())
                .error(error)
                .message(formatFailedMessageFromRequest(request))
                .type(ErrorTypeEnum.VALIDATION.name())
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.badRequest().body(apiFailureResponse);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiFailureResponse> handleNotFoundException(NoResourceFoundException noResourceFoundException, HttpServletRequest request) {
        ApiFailureResponse apiFailureResponse = ApiFailureResponse.builder()
                .status(noResourceFoundException.getStatusCode().value())
                .error(noResourceFoundException.getMessage())
                .message("404 not found, URL may be not exist")
                .type(ErrorTypeEnum.NOT_FOUND.name())
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiFailureResponse);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiFailureResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException httpRequestMethodNotSupportedException, HttpServletRequest request) {
        ApiFailureResponse apiFailureResponse = ApiFailureResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.METHOD_NOT_ALLOWED.value())
                .error(httpRequestMethodNotSupportedException.getMessage())
                .message(formatFailedMessageFromRequest(request))
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(apiFailureResponse);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiFailureResponse> handleEntityNotFoundException(EntityNotFoundException entityNotFoundException, HttpServletRequest request) {
        ApiFailureResponse apiFailureResponse = ApiFailureResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .error(entityNotFoundException.getMessage())
                .message(formatFailedMessageFromRequest(request))
                .type(ErrorTypeEnum.NOT_FOUND.name())
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiFailureResponse);
    }


    private String formatFailedMessageFromRequest(HttpServletRequest request){
        Object handler = request.getAttribute(HandlerMapping.BEST_MATCHING_HANDLER_ATTRIBUTE);
        String message = "Call API";
        if (handler instanceof HandlerMethod handlerMethod) {
            ApiRequestMessage apiRequestMessage = handlerMethod.getMethodAnnotation(ApiRequestMessage.class);
            if (apiRequestMessage != null) {
                message = apiRequestMessage.value();
            }
        }
        return message + " failed";
    }
}
