package com.spring.project.organicfoodshop.domain.response.management.brand;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Builder
public class AddedBrandResponse {
    private Long id;

    private String name;

    private String description;

    @JsonProperty("is_visible")
    private Boolean isVisible;

    @JsonProperty("is_deleted")
    private Boolean isDeleted;
}
