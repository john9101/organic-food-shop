package com.spring.project.organicfoodshop.domain.response.management.category;

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
}
