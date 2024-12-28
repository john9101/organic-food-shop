package com.spring.project.organicfoodshop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

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

    private String title;

    private String description;

    private Double discountPercent;

    private Integer quantity;
}
