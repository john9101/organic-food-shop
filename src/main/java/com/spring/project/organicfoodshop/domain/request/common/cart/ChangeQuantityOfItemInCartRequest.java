package com.spring.project.organicfoodshop.domain.request.common.cart;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeQuantityOfItemInCartRequest {
    @Min(value = 1, message = "Số lượng mua sản phảm phải ít nhất là {value}")
    private Integer quantity;
}