package com.spring.project.organicfoodshop.domain.response.common.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.project.organicfoodshop.domain.ProductImage;
import com.spring.project.organicfoodshop.util.constant.MeasurementUnitEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class GotProductDetailResponse {
    private Long id;

    private String title;

    private String name;

    private List<ProductImage> images;

    @JsonProperty("short_description")
    private String shortDescription;

    @JsonProperty("long_description")
    private String longDescription;

    @JsonProperty("regular_price")
    private Double regularPrice;

    @JsonProperty("discount_price")
    private Double discountPrice;

    @JsonProperty("discount_percent")
    private Double discountPercent;

    @JsonProperty("out_of_stock")
    private Boolean outOfStock;

    @JsonProperty("measurement_unit_mame")
    private String measurementUnitName;

    @JsonProperty("measurement_value")
    private Double measurementValue;

    @JsonProperty("brand_name")
    private String brandName;

    @JsonProperty("brand_id")
    private Long brandId;

    @JsonProperty("category_name")
    private String categoryName;

    @JsonProperty("category_id")
    private Long categoryId;
}
