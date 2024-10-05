package com.spring.project.organicfoodshop.service.validator;

import com.spring.project.organicfoodshop.domain.request.auth.RegisterRequest;
import com.spring.project.organicfoodshop.service.UserService;
import com.spring.project.organicfoodshop.util.annotation.AdvanceRequestBodyValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdvanceRegisterValidator implements ConstraintValidator<AdvanceRequestBodyValidation, RegisterRequest> {

    private final UserServiceValidator userServiceValidator;

    @Override
    public boolean isValid(RegisterRequest registerRequest, ConstraintValidatorContext constraintValidatorContext) {
        boolean valid = true;
        String password = registerRequest.getPassword();
        String confirmPassword = registerRequest.getConfirmPassword();
        String email = registerRequest.getEmail();
        String username = registerRequest.getUsername();

        if(password.length() < 6 && !password.trim().isEmpty()){
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("Password must be at least 6 characters")
                    .addPropertyNode("password")
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            valid = false;
        }

        if(!password.equals(confirmPassword)) {
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("Confirm password does not match")
                    .addPropertyNode("confirmPassword")
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            valid = false;
        }

        if(!userServiceValidator.validateEmailAndUsername(email, username, constraintValidatorContext)){
            valid = false;
        }

        return valid;
    }
}
