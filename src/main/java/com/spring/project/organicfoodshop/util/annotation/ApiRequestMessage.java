package com.spring.project.organicfoodshop.util.annotation;

import jakarta.validation.Constraint;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface ApiRequestMessage {
    String value();
}
