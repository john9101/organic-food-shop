package com.spring.project.organicfoodshop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.util.Set;

@Entity
@Table(name = "reviews")
@Getter
@Setter
public class Review extends AbstractAuditingEntity{
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @OneToOne
    @JoinColumn(name = "order_item_id")
    private OrderItem orderItem;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Boolean isVisible;

    private Boolean isDeleted;

    @PrePersist
    protected void onPrePersistComment() {
        this.isVisible = true;
        this.isDeleted = false;
    }
}
