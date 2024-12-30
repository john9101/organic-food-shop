package com.spring.project.organicfoodshop.domain.request.common.cart;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AddItemToCartRequest {
    private Long productId;

    private Integer quantity;
}
