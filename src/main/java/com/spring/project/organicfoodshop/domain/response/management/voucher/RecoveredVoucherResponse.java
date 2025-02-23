package com.spring.project.organicfoodshop.domain.response.management.voucher;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecoveredVoucherResponse {
    private Long id;

    @JsonProperty("is_deleted")
    private Boolean isDeleted;
}
