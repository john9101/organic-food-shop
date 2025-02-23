package com.spring.project.organicfoodshop.domain.response.management.brand;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GotBrandDetailResponse {
    private Long id;

    private String name;

    private String description;
}
