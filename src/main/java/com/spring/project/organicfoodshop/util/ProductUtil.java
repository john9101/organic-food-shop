package com.spring.project.organicfoodshop.util;

import com.spring.project.organicfoodshop.domain.Product;

import java.util.Optional;

public class ProductUtil {
    public static Double getProductPrice(Product product) {
        return Optional.ofNullable(product.getDiscountPrice()).orElse(product.getRegularPrice());
    }

    public static String getProductThumnnail(Product product) {
        return product.getImageUrls().stream().findFirst().orElse(null);
    }
}
