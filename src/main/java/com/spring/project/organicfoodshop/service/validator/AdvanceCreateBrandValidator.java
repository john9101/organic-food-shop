package com.spring.project.organicfoodshop.service.validator;

import com.spring.project.organicfoodshop.domain.request.management.brand.CreateBrandRequest;
import com.spring.project.organicfoodshop.service.BrandService;
import com.spring.project.organicfoodshop.util.annotation.AdvanceRequestBodyValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdvanceCreateBrandValidator implements ConstraintValidator<AdvanceRequestBodyValidation, CreateBrandRequest> {
    private final BrandService brandService;

    @Override
    public boolean isValid(CreateBrandRequest createBrandRequest, ConstraintValidatorContext constraintValidatorContext) {
        boolean valid = true;
        if (brandService.isExistsBrandByName(createBrandRequest.getName())) {
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("This name matches the name of a brand")
                    .addPropertyNode("name")
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            valid = false;
        }
        return valid;
    }
}
