package com.spring.project.organicfoodshop.domain.response.common.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditedAccountAddressResponse {
    private Long id;

    @JsonProperty("specific_place")
    private String specificPlace;

    private String province;

    private String district;

    private String commune;
}
