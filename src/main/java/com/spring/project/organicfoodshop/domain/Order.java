package com.spring.project.organicfoodshop.domain;

import com.spring.project.organicfoodshop.util.constant.OrderStatusEnum;
import com.spring.project.organicfoodshop.util.constant.PaymentMethodEnum;
import com.spring.project.organicfoodshop.util.constant.TransactionStatusEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order extends AbstractAuditingEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private PaymentMethodEnum paymentMethod;

    private String transactionReferenceCode;

    @Enumerated(EnumType.STRING)
    private TransactionStatusEnum transactionStatus;

    @Enumerated(EnumType.STRING)
    private OrderStatusEnum orderStatus;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderItem> orderItems = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String recipientFullName;

    private String recipientPhone;

    private String recipientEmail;

    private String recipientSpecificPlace;

    private String province;

    private String district;

    private String commune;

    private String note;

    private Double totalPrice;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "voucher_id")
    private Voucher voucher;

    @PrePersist
    protected void onPrePersistOrder() {
        this.transactionReferenceCode = this.id.toString();
        this.orderStatus = OrderStatusEnum.PENDING;
        this.totalPrice = this.orderItems.stream()
                .mapToDouble(orderItem -> orderItem.getPrice() * orderItem.getQuantity())
                .sum();
    }
}
