package com.spring.project.organicfoodshop.domain.response.common.cart;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder
public class GotCartSummaryResponse {
    private Long id;

    @JsonProperty("total_price")
    private Double totalPrice;

    @JsonProperty("total_count")
    private Integer totalCount;

    @JsonProperty("items")
    private Set<Item> items;

    @Getter
    @Setter
    @Builder
    public static class Item{
        private Long id;

        private Integer quantity;

        private Double price;

        private Double subtotal;

        @JsonProperty("product_id")
        private Long productId;

        @JsonProperty("product_title")
        private String productTitle;

        @JsonProperty("product_slug")
        private String productSlug;

        @JsonProperty("product_thumbnail")
        private String productThumbnail;
    }
}
