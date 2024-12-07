package com.spring.project.organicfoodshop.service.validator;

import com.spring.project.organicfoodshop.util.annotation.EnumValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class WeightUnitValidator implements ConstraintValidator<EnumValidation, String> {
    private Class<? extends Enum<?>> enumClass;


    @Override
    public void initialize(EnumValidation constraintAnnotation) {
        this.enumClass = constraintAnnotation.enumClass();
    }

    @Override
    public boolean isValid(String string, ConstraintValidatorContext constraintValidatorContext) {
        if (string == null || string.isEmpty()) {
            return true;
        }
        return Arrays.stream(enumClass.getEnumConstants())
                .anyMatch(e -> e.name().equals(string));
    }
}
