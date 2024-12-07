package com.spring.project.organicfoodshop.service.validator;

import com.spring.project.organicfoodshop.domain.request.management.product.CreateProductRequest;
import com.spring.project.organicfoodshop.service.ProductService;
import com.spring.project.organicfoodshop.util.annotation.AdvanceRequestBodyValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdvanceCreateProductValidator implements ConstraintValidator<AdvanceRequestBodyValidation, CreateProductRequest> {
    private final ProductService productService;

    @Override
    public boolean isValid(CreateProductRequest createProductRequest, ConstraintValidatorContext constraintValidatorContext) {
        boolean valid = true;
        if (productService.isExistsProductByName(createProductRequest.getName())) {
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("This name matches the name of a product")
                    .addPropertyNode("name")
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            valid = false;
        }
        return valid;
    }
}
