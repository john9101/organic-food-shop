package com.spring.project.organicfoodshop.domain.response.common.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder
public class CategoryItemResponse {
    private String name;

    private String slug;

    @JsonProperty("sub_category_items")
    private Set<ChildCategoryItem> childCategoryItems;

    @Getter
    @Setter
    @Builder
    public static class ChildCategoryItem {
        private String name;
        private String slug;
    }
}
