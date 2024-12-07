package com.spring.project.organicfoodshop.util.annotation;

import com.spring.project.organicfoodshop.service.validator.WeightUnitValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {
        WeightUnitValidator.class
})
public @interface EnumValidation {
    Class<? extends Enum<?>> enumClass();
    String message() default "Value is not valid. Must be one of the enum values";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
