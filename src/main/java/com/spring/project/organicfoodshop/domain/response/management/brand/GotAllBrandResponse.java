package com.spring.project.organicfoodshop.domain.response.management.brand;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.project.organicfoodshop.domain.Product;
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

        private String description;

        @JsonProperty("is_visible")
        private Boolean isVisible;

        @JsonProperty("is_deleted")
        private Boolean isDeleted;
    }
}
