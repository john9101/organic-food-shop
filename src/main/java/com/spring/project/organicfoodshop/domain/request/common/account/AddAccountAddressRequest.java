package com.spring.project.organicfoodshop.domain.request.common.account;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddAccountAddressRequest {
    private String specificPlace;

    private String province;

    private String district;

    private String commune;
}
