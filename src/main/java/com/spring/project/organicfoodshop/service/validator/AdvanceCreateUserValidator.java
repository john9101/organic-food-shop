package com.spring.project.organicfoodshop.service.validator;

import com.spring.project.organicfoodshop.domain.request.management.user.CreateUserRequest;
import com.spring.project.organicfoodshop.util.annotation.AdvanceRequestBodyValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdvanceCreateUserValidator implements ConstraintValidator<AdvanceRequestBodyValidation, CreateUserRequest> {
    private final UserServiceValidator userServiceValidator;

    @Override
    public boolean isValid(CreateUserRequest createUserRequest, ConstraintValidatorContext constraintValidatorContext) {
        boolean valid = true;
        String email = createUserRequest.getEmail();
        String username = createUserRequest.getUsername();

        if(!userServiceValidator.validateEmailAndUsername(email, username, constraintValidatorContext)){
            valid = false;
        }

        return valid;
    }
}
