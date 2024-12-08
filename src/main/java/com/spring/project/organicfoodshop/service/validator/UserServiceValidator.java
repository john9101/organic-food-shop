package com.spring.project.organicfoodshop.service.validator;

import com.spring.project.organicfoodshop.service.UserService;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceValidator {
    private final UserService userService;

    public boolean validateEmailAndUsername(String email, String username, ConstraintValidatorContext constraintValidatorContext) {
        boolean valid = true;
        if(userService.ixExistsUserByEmail(email)) {
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("This email has been registered by another account")
                    .addPropertyNode("email")
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            valid = false;
        }

        if (userService.isExistsUserByUsername(username)) {
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("This username has been registered by another account")
                    .addPropertyNode("username")
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            valid = false;
        }
        return valid;
    }
}
