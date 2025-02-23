package com.spring.project.organicfoodshop.domain.response.common.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DeletedAccountAddressResponse {
    private Long id;
}
