package com.spring.project.organicfoodshop.domain.response.common.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder
public class ProductItemResponse {
    private Long id;

    private String name;

    private String slug;

    @JsonProperty("short_description")
    private String shortDescription;

    @JsonProperty("image_urls")
    private Set<String> imageUrls;

    private Double rating;

    @JsonProperty("out_of_stock")
    private Boolean outOfStock;

    @JsonProperty("price_use_for_sort")
    private Double priceUseForSort;

    @JsonProperty("selling_price")
    private Double sellingPrice;

    @JsonProperty("discount_percent_event")
    private Double discountPercentEvent;
}


