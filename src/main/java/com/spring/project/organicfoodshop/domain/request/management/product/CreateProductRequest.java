package com.spring.project.organicfoodshop.domain.request.management.product;

import com.spring.project.organicfoodshop.util.annotation.AdvanceRequestBodyValidation;
import com.spring.project.organicfoodshop.util.constant.MeasurementUnitTypeEnum;
import com.spring.project.organicfoodshop.util.constant.SellingUnitTypeEnum;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder
@AdvanceRequestBodyValidation
public class CreateProductRequest {
    @NotBlank(message = "Name can not be blank")
    private String name;

    @NotNull(message = "Short description can not be blank")
    private String shortDescription;

    @NotNull(message = "Long description can not be blank")
    private String longDescription;

    @NotNull(message = "Image urls can not be null")
    @Size(min = 1, message = "Product must have at least 1 image")
    private Set<String> imageUrls;

    @NotBlank(message = "Slug can not be blank")
    private String slug;

    @NotNull(message = "Measurement value can not be null")
    private Double measurementValue;

    @NotBlank(message = "Measurement unit can not be blank")
    private MeasurementUnitTypeEnum measurementUnit;

    @NotNull(message = "Selling price can not be null")
    private Double sellingPrice;

    private SellingUnitTypeEnum sellingUnit;

    @NotNull(message = "Quantity in stock can not be null")
    @Min(value = 1, message = "Quantity in stock must be greater than or equal 1")
    private Integer quantityInStock;

    @NotNull(message = "Brand must be selected")
    private Long brandId;

    @NotNull(message = "Category must be selected")
    @Size(min = 1, message = "Product must have at least 1 category")
    private Set<Long> categoryIds;
}
