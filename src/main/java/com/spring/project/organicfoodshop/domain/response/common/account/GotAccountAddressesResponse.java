package com.spring.project.organicfoodshop.domain.response.common.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder
public class GotAccountAddressesResponse {
    private Set<Item> items;

    @Getter
    @Setter
    public static class Item{
        private Long id;

        @JsonProperty("specific_place")
        private String specificPlace;

        private String province;

        private String district;

        private String commune;
    }
}
