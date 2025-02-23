package com.spring.project.organicfoodshop.domain.response.common.voucher;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.project.organicfoodshop.domain.Voucher;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
public class GotAllVouchersResponse {
    private List<Item> items;

    @Getter
    @Setter
    public static class Item{
        private Long id;

        private String code;

        @JsonProperty("discount_percent")
        private Integer discountPercent;

        private Integer quantity;

        @JsonProperty("effective_date")
        private LocalDate effectiveDate;

        @JsonProperty("expiry_date")
        private LocalDate expiryDate;

        @JsonProperty("is_visible")
        private Boolean isVisible;
    }
}
