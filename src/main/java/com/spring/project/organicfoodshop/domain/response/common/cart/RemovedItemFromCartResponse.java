package com.spring.project.organicfoodshop.domain.response.common.cart;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RemovedItemFromCartResponse {
    @JsonProperty("total_count")
    private Integer totalCount;

    @JsonProperty("item_id")
    private Long itemId;
}
