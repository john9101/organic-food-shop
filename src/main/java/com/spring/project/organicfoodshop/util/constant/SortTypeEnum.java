package com.spring.project.organicfoodshop.util.constant;

import lombok.Getter;

@Getter
public enum SortTypeEnum {
    PRICE_ASC("price-asc"),
    PRICE_DESC("price-desc"),
    RELEVANCE("relevance");

    private final String value;
    SortTypeEnum(String value) {
        this.value = value;
    }
}
