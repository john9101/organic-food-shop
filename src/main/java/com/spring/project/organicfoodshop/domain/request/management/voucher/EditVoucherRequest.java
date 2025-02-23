package com.spring.project.organicfoodshop.domain.request.management.voucher;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EditVoucherRequest {
    private String code;

    private String description;

    private Integer discountPercent;

    private Integer quantity;

    private Double minimumAmount;

    private LocalDate effectiveDate;

    private LocalDate expiryDate;
}
