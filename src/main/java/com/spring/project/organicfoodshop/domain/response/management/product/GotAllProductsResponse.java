package com.spring.project.organicfoodshop.domain.response.management.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class GotAllProductsResponse {
    private List<Item> items;

    @Getter
    @Setter
    public static class Item{
        private Long id;

        private String title;

        @JsonProperty("regular_price")
        private Double regularPrice;

        @JsonProperty("discount_price")
        private Double discountPrice;

        @JsonProperty("is_visible")
        private Boolean isVisible;

        @JsonProperty("discount_percent")
        private Integer discountPercent;
    }
}
