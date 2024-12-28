package com.spring.project.organicfoodshop.domain.response.common.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class GotProductDetailResponse {
    private Long id;

    private String title;

    private String slug;

    @JsonProperty("image_urls")
    private Set<String> imageUrls;

    @JsonProperty("short_description")
    private String shortDescription;

    @JsonProperty("long_description")
    private String longDescription;

    @JsonProperty("regular_price")
    private Double regularPrice;

    @JsonProperty("discount_price")
    private Double discountPrice;

    @JsonProperty("discount_percent_event")
    private Double discountPercentEvent;

    @JsonProperty("out_of_stock")
    private Boolean outOfStock;

    @JsonProperty("brand_info")
    private BrandInfo brandInfo;

    @JsonProperty("category_infos")
    private Set<CategoryInfo> categoryInfos;

    @Getter
    @Setter
    public static class BrandInfo{
        private String name;

        @JsonProperty("image_url")
        private String imageUrl;
    }

    @Getter
    @Setter
    public static class CategoryInfo{
        private String name;
        private String slug;
    }
}
