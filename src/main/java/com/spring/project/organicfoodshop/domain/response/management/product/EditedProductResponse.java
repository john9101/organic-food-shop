package com.spring.project.organicfoodshop.domain.response.management.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EditedProductResponse {
    private Long id;

    private String title;

    @JsonProperty("regular_price")
    private Double regularPrice;

    @JsonProperty("discount_percent")
    private Double dicountPercent;

    @JsonProperty("discount_price")
    private Double discountPrice;

    @JsonProperty("brand_name")
    private String brandName;

    @JsonProperty("category_name")
    private String categoryName;
}
