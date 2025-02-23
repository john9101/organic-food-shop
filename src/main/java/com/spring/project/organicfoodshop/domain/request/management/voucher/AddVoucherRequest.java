package com.spring.project.organicfoodshop.domain.request.management.voucher;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AddVoucherRequest {
    private String code;

    private String description;

    private Integer minimumAmount;

    private Integer discountPercent;

    private LocalDate effectiveDate;

    private LocalDate expiryDate;

    private Integer quantity;
}
