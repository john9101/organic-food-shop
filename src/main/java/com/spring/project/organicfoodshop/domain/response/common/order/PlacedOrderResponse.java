package com.spring.project.organicfoodshop.domain.response.common.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.project.organicfoodshop.util.constant.PaymentMethodEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Builder
@Getter
@Setter
public class PlacedOrderResponse {
    private UUID id;

    @JsonProperty("payment_method")
    private PaymentMethodEnum paymentMethod;

    @JsonProperty("payment_url")
    private String paymentUrl;
}
