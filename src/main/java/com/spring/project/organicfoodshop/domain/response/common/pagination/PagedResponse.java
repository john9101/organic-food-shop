package com.spring.project.organicfoodshop.domain.response.common.pagination;

import com.fasterxml.jackson.annotation.JsonProperty;
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
        private Long id;

        @JsonProperty("short_description")
        private String shortDescription;

        private String title;

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
