package com.spring.project.organicfoodshop.domain;

import com.spring.project.organicfoodshop.util.constant.SellingUnitTypeEnum;
import com.spring.project.organicfoodshop.util.constant.MeasurementUnitTypeEnum;
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

    private String name;

    private String slug;

    private String shortDescription;

    private String longDescription;

//    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private Set<ProductWeight> productWeights = new HashSet<>();

//    @Convert(converter = SellingUnitTypeEnum.class)
    @Enumerated(EnumType.STRING)
    private SellingUnitTypeEnum sellingUnit;

    @Enumerated(EnumType.STRING)
    private MeasurementUnitTypeEnum measurementUnit;

    private Double measurementValue;

    private Double sellingPrice;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ProductEvent> productEvents;

    private Double rating;

    @ElementCollection
    private Set<String> imageUrls;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "products_categories",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    private Boolean visible;

    private Integer quantityInStock;

    private Double priceUseForSort;

    private Double discountPercentEvent;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<CartItem> cartItems;

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
