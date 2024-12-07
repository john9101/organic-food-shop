package com.spring.project.organicfoodshop.domain.response.common.pagination;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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
}
