package com.spring.project.organicfoodshop.domain.response.management.brand;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DisplayedBrandResponse {
    private Long id;

    @JsonProperty("is_visible")
    private Boolean isVisible;
}
