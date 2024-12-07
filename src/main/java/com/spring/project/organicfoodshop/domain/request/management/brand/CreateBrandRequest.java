package com.spring.project.organicfoodshop.domain.request.management.brand;

import com.spring.project.organicfoodshop.util.annotation.AdvanceRequestBodyValidation;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AdvanceRequestBodyValidation
public class CreateBrandRequest {
    @NotBlank(message = "Name can not be blank")
    private String name;

    @NotBlank(message = "Description can not be blank")
    private String description;

//    @NotBlank(message = "Image url can not be blank")
    private String imageUrl;
}
