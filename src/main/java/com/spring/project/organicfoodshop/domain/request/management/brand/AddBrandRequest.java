package com.spring.project.organicfoodshop.domain.request.management.brand;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AddBrandRequest {
    private String name;
}
