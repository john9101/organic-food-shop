package com.spring.project.organicfoodshop.domain.response.management.brand;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class GotAllBrandResponse {

    private List<Item> items;

    @Getter
    @Setter
    public static class Item{
        private Long id;

        private String name;
    }
}
