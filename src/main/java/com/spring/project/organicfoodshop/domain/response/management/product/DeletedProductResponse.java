package com.spring.project.organicfoodshop.domain.response.management.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DeletedProductResponse {
    private Long id;

    @JsonProperty("is_deleted")
    private Boolean isDeleted;
}
