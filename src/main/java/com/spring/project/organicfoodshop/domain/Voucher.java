package com.spring.project.organicfoodshop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.time.LocalDate;

@Entity
@Table(name = "vouchers")
@Getter
@Setter
public class Voucher extends AbstractAuditingEntity{
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private Double minimumAmount;

    private String description;

    private Integer discountPercent;

    private Integer quantity;

    private LocalDate effectiveDate;

    private LocalDate expiryDate;

    private Boolean isVisible;

    private Boolean isDeleted;

    @PrePersist
    protected void onPrePersistVoucher() {
        this.isVisible = true;
        this.isDeleted = false;
    }
}
