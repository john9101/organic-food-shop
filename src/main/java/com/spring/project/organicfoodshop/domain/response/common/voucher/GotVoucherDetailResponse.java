package com.spring.project.organicfoodshop.domain.response.common.voucher;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class GotVoucherDetailResponse {
    private Long id;

    private String code;

    private String description;

    @JsonProperty("discount_percent")
    private Integer discountPercent;

    private Integer quantity;

    @JsonProperty("minimum_amount")
    private Double minimumAmount;

    @JsonProperty("effective_date")
    private LocalDate effectiveDate;

    @JsonProperty("expiry_date")
    private LocalDate expiryDate;
}
