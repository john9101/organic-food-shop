package com.spring.project.organicfoodshop.util.annotation;

import com.spring.project.organicfoodshop.service.validator.AdvanceCreateUserValidator;
import com.spring.project.organicfoodshop.service.validator.AdvanceRegisterValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = {
        AdvanceRegisterValidator.class,
        AdvanceCreateUserValidator.class
})
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AdvanceRequestBodyValidation {
    String message() default "";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
