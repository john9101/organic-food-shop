package com.spring.project.organicfoodshop.service.validator;

import com.spring.project.organicfoodshop.domain.request.common.cart.AddItemToCartRequest;
import com.spring.project.organicfoodshop.service.ProductService;
import com.spring.project.organicfoodshop.util.annotation.AdvanceRequestBodyValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.hibernate.action.internal.EntityActionVetoException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdvanceAddToCartValidator implements ConstraintValidator<AdvanceRequestBodyValidation, AddItemToCartRequest> {
    private final ProductService productService;
    @Override
    public boolean isValid(AddItemToCartRequest addToCartRequest, ConstraintValidatorContext constraintValidatorContext) {
        boolean valid = true;
        Long productId = addToCartRequest.getProductId();
        try {
            productService.getProductById(productId);
        }catch (EntityActionVetoException exception){
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate(exception.getMessage())
                    .addPropertyNode("productId")
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            valid = false;
        }
        return valid;
    }
}
