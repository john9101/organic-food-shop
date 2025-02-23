package com.spring.project.organicfoodshop.domain.response.management.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.project.organicfoodshop.util.constant.OrderStatusEnum;
import com.spring.project.organicfoodshop.util.constant.PaymentMethodEnum;
import com.spring.project.organicfoodshop.util.constant.TransactionStatusEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
public class EditedOrderResponse {
    private UUID id;

    @JsonProperty("total_price")
    private Double totalPrice;

    @JsonProperty("payment_method")
    private PaymentMethodEnum paymentMethod;

    @JsonProperty("order_status")
    private OrderStatusEnum orderStatus;

    @JsonProperty("transaction_status")
    private TransactionStatusEnum transactionStatus;

    @JsonProperty("created_at")
    private Instant createdAt;

    @JsonProperty("recipient_full_name")
    private String recipientFullName;

    @JsonProperty("customer_id")
    private Long customerId;

    @JsonProperty("recipient_phone")
    private String recipientPhone;
}
