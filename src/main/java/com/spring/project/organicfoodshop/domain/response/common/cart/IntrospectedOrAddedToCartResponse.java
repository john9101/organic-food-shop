package com.spring.project.organicfoodshop.domain.response.common.cart;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder
public class IntrospectedOrAddedToCartResponse {

    @JsonProperty("cart_id")
    private Long cartId;

    @JsonProperty("cart_item_infos")
    private Set<ExtractedCartItemResponse> cartItemInfos;
}
