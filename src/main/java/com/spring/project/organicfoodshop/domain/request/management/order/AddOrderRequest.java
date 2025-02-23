package com.spring.project.organicfoodshop.domain.request.management.order;

import com.spring.project.organicfoodshop.util.constant.PaymentMethodEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AddOrderRequest {
    private Long customerId;
    private String recipientFullName;
    private String recipientPhone;
    private String recipientEmail;
    private String recipientSpecificPlace;
    private String province;
    private String district;
    private String commune;
    private String note;
    private List<Item> items;
    private PaymentMethodEnum paymentMethod;
    private Long voucherId;

    @Getter
    @Setter
    public static class Item{
        private Long productId;
        private Integer quantity;
    }
}
