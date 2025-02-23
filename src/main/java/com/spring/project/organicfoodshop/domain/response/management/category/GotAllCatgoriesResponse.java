package com.spring.project.organicfoodshop.domain.response.management.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class GotAllCatgoriesResponse {
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
