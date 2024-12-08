package com.spring.project.organicfoodshop.domain.response.common.cart;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ExtractedCartItemResponse {
        @JsonProperty("product_id")
        private Long productId;

        @JsonProperty("product_slug")
        private String productSlug;

        @JsonProperty("product_thumbnail")
        private String productThumbnail;

        @JsonProperty("product_name")
        private String productName;

        private Integer quantity;

        private Double price;
}
