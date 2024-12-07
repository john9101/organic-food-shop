package com.spring.project.organicfoodshop.domain.response.common.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class GotDetailInfoProductResponse {
    private Long id;

    private String name;

    private String slug;

    @JsonProperty("image_urls")
    private Set<String> imageUrls;

    @JsonProperty("short_description")
    private String shortDescription;

    @JsonProperty("long_description")
    private String longDescription;

    @JsonProperty("selling_price")
    private Double sellingPrice;

    @JsonProperty("selling_unit_display")
    private String sellingUnitDisplay;

    @JsonProperty("measurement_unit_display")
    private String measurementUnitDisplay;

    @JsonProperty("measurement_value")
    private Double measurementValue;

    @JsonProperty("discount_percent_event")
    private Double discountPercentEvent;

    @JsonProperty("price_use_for_sort")
    private Double priceUseForSort;

    @JsonProperty("out_of_stock")
    private Boolean outOfStock;

    @JsonProperty("brand_info")
    private ProductBrandInfo brandInfo;

    @JsonProperty("category_infos")
    private Set<ProductCategoryInfo> categoryInfos;

    @Getter
    @Setter
    public static class ProductBrandInfo{
        private String name;

        @JsonProperty("image_url")
        private String imageUrl;
    }

    @Getter
    @Setter
    public static class ProductCategoryInfo{
        private String name;
        private String slug;
    }
}
