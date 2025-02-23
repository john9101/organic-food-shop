package com.spring.project.organicfoodshop.domain.response.management.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AddedCategoryResponse {
    private Long id;

    private String name;

    private String description;

    @JsonProperty("is_visible")
    private Boolean isVisible;

    @JsonProperty("is_deleted")
    private Boolean isDeleted;
}
