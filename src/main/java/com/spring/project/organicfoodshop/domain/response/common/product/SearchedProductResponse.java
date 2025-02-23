package com.spring.project.organicfoodshop.domain.response.common.product;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class SearchedProductResponse {
    private List<Item> items;

    @Getter
    @Setter
    public static class Item{
        private Long id;
        private String title;
    }
}
