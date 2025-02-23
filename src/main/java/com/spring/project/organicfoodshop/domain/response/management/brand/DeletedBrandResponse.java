package com.spring.project.organicfoodshop.domain.response.management.brand;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeletedBrandResponse {
    private Long id;

    @JsonProperty("is_deleted")
    private Boolean isDeleted;
}
