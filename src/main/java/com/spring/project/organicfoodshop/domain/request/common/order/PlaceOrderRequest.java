package com.spring.project.organicfoodshop.domain.request.common.order;

import com.spring.project.organicfoodshop.util.constant.PaymentMethodEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlaceOrderRequest {
    private String fullName;

    private String phone;

    private String email;

    private String address;

    private String province;

    private String district;

    private String commune;

    private String note;

    private PaymentMethodEnum paymentMethod;
}
