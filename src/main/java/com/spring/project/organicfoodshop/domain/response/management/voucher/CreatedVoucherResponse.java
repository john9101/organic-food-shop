package com.spring.project.organicfoodshop.domain.response.management.voucher;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatedVoucherResponse {
    private Long id;

    private String title;

    private String description;

    @JsonProperty("discount_percent")
    private Double discountPercent;

    private Integer quantity;
}
