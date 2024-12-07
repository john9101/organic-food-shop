package com.spring.project.organicfoodshop.domain.request.common.pagination;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PagingRequest {
    private Integer page;
    private String sort;
    private Integer size;
}
