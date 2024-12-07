package com.spring.project.organicfoodshop.domain.request.management.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateCategoryRequest {
    @NotBlank(message = "Name can not be blank")
    private String name;

    private String slug;

    @NotNull(message = "Parent category must be selected")
    private Long parentCategoryId;
}
