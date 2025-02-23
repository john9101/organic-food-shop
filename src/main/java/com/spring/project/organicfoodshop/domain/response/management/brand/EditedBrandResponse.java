package com.spring.project.organicfoodshop.domain.response.management.brand;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EditedBrandResponse {
    private Long id;

    private String name;

    private String description;
}
