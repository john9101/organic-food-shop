package com.spring.project.organicfoodshop.domain.response.management.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DisplayedProductResponse {
    private Long id;

    @JsonProperty("is_visible")
    private Boolean isVisible;
}
