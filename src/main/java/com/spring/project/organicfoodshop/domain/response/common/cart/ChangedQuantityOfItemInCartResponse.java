package com.spring.project.organicfoodshop.domain.response.common.cart;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ChangedQuantityOfItemInCartResponse {
    @JsonProperty("item_id")
    private Long itemId;

    @JsonProperty("item_quantity")
    private Integer itemQuantity;
}
