package com.spring.project.organicfoodshop.util.constant;
import lombok.Getter;

@Getter
public enum ProductSortEnum {
    PRICE_ASC("price-asc"),
    PRICE_DESC("price-desc");

    private final String strategy;
    ProductSortEnum(String Strategy) {
        this.strategy = Strategy;
    }
}
