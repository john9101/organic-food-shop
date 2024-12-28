package com.spring.project.organicfoodshop.domain.response.common.pagination;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.project.organicfoodshop.util.constant.MeasurementUnitEnum;
import com.spring.project.organicfoodshop.util.constant.SellingUnitEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
public class PagedResponse<T> {
    private List<T> items;

    @JsonProperty("total_pages")
    private Integer totalPages;

    @JsonProperty("total_items")
    private Integer totalItems;

    @JsonProperty("current_page")
    private Integer currentPage;


    @Getter
    @Setter
    public static class ProductItem{
        private String name;

        private String slug;

        @JsonProperty("short_description")
        private String shortDescription;

        @JsonProperty("selling_unit")
        private SellingUnitEnum sellingUnit;

        @JsonProperty("measurement_unit")
        private MeasurementUnitEnum measurementUnit;

        @JsonProperty("measurement_value")
        private Double measurementValue;

        @JsonProperty("regular_price")
        private Double regularPrice;

        @JsonProperty("discount_price")
        private Double discountPrice;

        private Double rating;

        @JsonProperty("image_urls")
        private Set<String> imageUrls;

        @JsonProperty("discount_percent_event")
        private Double discountPercentEvent;
    }
}
