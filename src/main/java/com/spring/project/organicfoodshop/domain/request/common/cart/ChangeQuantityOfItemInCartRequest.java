package com.spring.project.organicfoodshop.domain.request.common.cart;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeQuantityOfItemInCartRequest {
    private Integer quantity;
}