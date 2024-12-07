package com.spring.project.organicfoodshop.domain.request.common.product;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.spring.project.organicfoodshop.domain.request.common.pagination.PagingRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder
public class GetProductItemsRequest {
    @JsonUnwrapped
    private PagingRequest pagingRequest;
    private Set<Long> brandIds;
    private Double rating;
    private Double minPrice;
    private Double maxPrice;
}


