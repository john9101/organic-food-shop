package com.spring.project.organicfoodshop.domain.request.management.voucher;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditVoucherRequest {
    private String title;

    private String description;

    private Double discountPercent;

    private Integer quantity;
}
