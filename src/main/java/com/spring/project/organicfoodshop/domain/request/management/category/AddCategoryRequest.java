package com.spring.project.organicfoodshop.domain.request.management.category;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AddCategoryRequest {
    private String name;
}
