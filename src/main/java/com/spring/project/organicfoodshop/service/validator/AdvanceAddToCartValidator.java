package com.spring.project.organicfoodshop.service.validator;

import com.spring.project.organicfoodshop.domain.request.common.cart.AddToCartRequest;
import com.spring.project.organicfoodshop.service.ProductService;
import com.spring.project.organicfoodshop.util.annotation.AdvanceRequestBodyValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdvanceAddToCartValidator implements ConstraintValidator<AdvanceRequestBodyValidation, AddToCartRequest> {
    private final ProductService productService;
    @Override
    public boolean isValid(AddToCartRequest addToCartRequest, ConstraintValidatorContext constraintValidatorContext) {
        boolean valid = true;
        Long productId = addToCartRequest.getProductId();
        System.out.println(productId);
        if (productId != null) {
            if (!productService.isExistsProductById(productId)){
                constraintValidatorContext
                        .buildConstraintViolationWithTemplate(String.format("Không tìm thấy sản phẩm với mã là %d", productId))
                        .addPropertyNode("productId")
                        .addConstraintViolation()
                        .disableDefaultConstraintViolation();
                valid = false;
            }
        }
        return valid;
    }
}
