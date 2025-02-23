package com.spring.project.organicfoodshop.domain.response.management.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeletedCategoryResponse {
    private Long id;

    @JsonProperty("is_deleted")
    private Boolean isDeleted;
}
