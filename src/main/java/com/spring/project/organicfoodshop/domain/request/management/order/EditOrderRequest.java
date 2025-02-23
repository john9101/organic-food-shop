package com.spring.project.organicfoodshop.domain.request.management.order;

import com.spring.project.organicfoodshop.util.constant.OrderStatusEnum;
import com.spring.project.organicfoodshop.util.constant.PaymentMethodEnum;
import com.spring.project.organicfoodshop.util.constant.TransactionStatusEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EditOrderRequest {
    private String recipientFullName;
    private String recipientPhone;
    private String recipientEmail;
    private String recipientSpecificPlace;
    private String province;
    private String district;
    private String commune;
    private String note;
    private PaymentMethodEnum paymentMethod;
    private TransactionStatusEnum transactionStatus;
    private OrderStatusEnum orderStatus;
}
