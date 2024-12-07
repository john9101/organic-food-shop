package com.spring.project.organicfoodshop.service.validator;

import com.spring.project.organicfoodshop.domain.request.management.category.CreateCategoryRequest;
import com.spring.project.organicfoodshop.service.CategoryService;
import com.spring.project.organicfoodshop.util.annotation.AdvanceRequestBodyValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdvanceCreateCategoryValidator implements ConstraintValidator<AdvanceRequestBodyValidation, CreateCategoryRequest> {
    private final CategoryService categoryService;

    @Override
    public boolean isValid(CreateCategoryRequest createCategoryRequest, ConstraintValidatorContext constraintValidatorContext) {
        boolean valid = true;
        String name = createCategoryRequest.getName();
        String slug = createCategoryRequest.getSlug();

        if (categoryService.getCategoryByName(name) != null){
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("This name matches the name of a category")
                    .addPropertyNode("name")
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            valid = false;
        }

        if (slug != null && categoryService.getCategoryBySlug(slug) != null){
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("This slug matches the name of a category")
                    .addPropertyNode("slug")
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            valid = false;
        }

        return valid;
    }
}
