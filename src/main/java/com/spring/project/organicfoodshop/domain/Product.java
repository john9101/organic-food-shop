package com.spring.project.organicfoodshop.domain;

import com.spring.project.organicfoodshop.util.FormatterUtil;
import com.spring.project.organicfoodshop.util.constant.MeasurementUnitEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Product extends AbstractAuditingEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String shortDescription;

    private String longDescription;

    private String title;

    @Enumerated(EnumType.STRING)
    private MeasurementUnitEnum measurementUnit;

    private Double measurementValue;

    private Double regularPrice;

    private Double discountPrice;

    private Double rating;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProductImage> images = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    private Integer quantityInStock;

    private Integer discountPercent;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<CartItem> cartItems;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Comment> comments;

    private Boolean isVisible;

    private Boolean isDeleted;

    @PrePersist
    private void prePersistProduct() {
        this.isVisible = true;
        this.isDeleted = false;
        this.rating = 0.0;
        this.title = FormatterUtil.formatProductTitle(this.name, this.measurementValue, this.measurementUnit);
        if (discountPercent != null) {
            this.discountPrice = this.regularPrice * (1 - this.discountPercent / 100);
        }
    }
//    @PrePersist
//    public void handlePersistProduct() {
//        this.visible = true;
//        this.outOfStock = false;
//        this.rating = 0.0;
//        this.priceUseForSort = this.productWeights.stream()
//                .mapToDouble(ProductWeight::getPrice)
//                .min()
//                .orElse(0.0);
//    }
//
//    @PreUpdate
//    public void handleUpdateProduct() {
//        productEvents.stream()
//                .filter(productEvent -> !productEvent.getEvent().getExpired())
//                .findFirst()
//                .ifPresent(productEvent -> {
//                    this.discountPercentEvent = productEvent.getDiscountPercent();
//                    this.priceUseForSort = productWeights.stream()
//                            .mapToDouble(productWeight -> productWeight.getPrice() * (1 - discountPercentEvent))
//                            .min()
//                            .orElse(this.priceUseForSort);
//                });
//    }
}
