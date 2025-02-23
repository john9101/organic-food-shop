package com.spring.project.organicfoodshop.domain.response.management.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GotCategoryDetailResponse {
    private Long id;

    private String name;

    private String description;

    @JsonProperty("is_visible")
    private Boolean isVisible;
}
