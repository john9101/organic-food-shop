package com.spring.project.organicfoodshop.domain.request.management.category;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditCategoryRequest {
    private String name;
    private String description;
}
