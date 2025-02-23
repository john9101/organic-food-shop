package com.spring.project.organicfoodshop.domain.response.common.cart;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AddedItemToCartResponse {

    @JsonProperty("total_count")
    private Integer totalCount;

    @JsonProperty("item_id")
    private Long itemId;

    @JsonProperty("item_product_id")
    private Long itemProductId;

    @JsonProperty("item_title")
    private String itemTitle;

    @JsonProperty("item_quantity")
    private Integer itemQuantity;

    @JsonProperty("item_price")
    private Double itemPrice;

    @JsonProperty("item_thumbnail")
    private String itemThumbnail;
}
