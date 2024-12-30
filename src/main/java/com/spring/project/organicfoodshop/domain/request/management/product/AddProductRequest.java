package com.spring.project.organicfoodshop.domain.request.management.product;

import com.spring.project.organicfoodshop.util.constant.MeasurementUnitEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AddProductRequest {
    private String name;

    private String shortDescription;

    private String longDescription;

    private Double measurementValue;

    private MeasurementUnitEnum measurementUnit;

    private Double regularPrice;

    private Double discountPercent;

    private Integer quantityInStock;

    private Long brandId;

    private Long categoryId;
}
