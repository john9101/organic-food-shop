package com.spring.project.organicfoodshop.domain.response.management.voucher;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EditedVoucherResponse {
    private Long id;

    private String code;

    @JsonProperty("discount_percent")
    private Double discountPercent;

    private Integer quantity;

    @JsonProperty("effective_date")
    private LocalDate effectiveDate;

    @JsonProperty("expiry_date")
    private LocalDate expiryDate;
}
