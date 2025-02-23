package com.spring.project.organicfoodshop.domain.response.management.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.project.organicfoodshop.util.constant.OrderStatusEnum;
import com.spring.project.organicfoodshop.util.constant.PaymentMethodEnum;
import com.spring.project.organicfoodshop.util.constant.TransactionStatusEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class GotOrderDetailResponse {
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

    @JsonProperty("recipient_email")
    private String recipientEmail;

    @JsonProperty("customer_id")
    private Long customerId;

    @JsonProperty("customer_full_name")
    private String customerFullName;

    @JsonProperty("recipient_phone")
    private String recipientPhone;

    @JsonProperty("recipient_specific_place")
    private String recipientSpecificPlace;

    private String province;

    private String district;

    private String commune;

    private String note;

    @JsonProperty("voucher_id")
    private Long voucherId;

    @JsonProperty("voucher_title")
    private String voucherTitle;

    private List<Item> items;

    @Getter
    @Setter
    @Builder
    public static class Item{
        private Long id;

        private Double price;

        @JsonProperty("product_id")
        private Long productId;

        @JsonProperty("product_title")
        private String productTitle;

        private Integer quantity;
    }
}
