package com.spring.project.organicfoodshop.service.specification;

import com.spring.project.organicfoodshop.domain.Product;
import com.spring.project.organicfoodshop.util.constant.ProductSortEnum;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiFunction;

public class ProductSpecification {

    private static final Map<ProductSortEnum, BiFunction<Root<Product>, CriteriaBuilder, Order>> PRODUCT_SORT_LOGICS = Map.of(
            ProductSortEnum.PRICE_ASC, (root, cb) -> cb.asc(getPriceExpression(root, cb)),
            ProductSortEnum.PRICE_DESC, (root, cb) -> cb.desc(getPriceExpression(root, cb))
    );

    private static Expression<Double> getPriceExpression(Root<Product> root, CriteriaBuilder cb) {
        return cb.coalesce(root.get("discountPrice"), root.get("regularPrice"));
    }

    public static Specification<Product> filterByPriceRange(Double minPrice, Double maxPrice) {
        return (root, query, cb) -> {
            if (minPrice == null && maxPrice == null) return null;
            Expression<Double> priceExpression = getPriceExpression(root, cb);
            if (minPrice != null && maxPrice != null) {
                return cb.between(priceExpression, minPrice, maxPrice);
            } else if (minPrice != null) {
                return cb.ge(priceExpression, minPrice);
            } else {
                return cb.le(priceExpression, maxPrice);
            }
        };
    }

    public static Specification<Product> filterByRating(Double rating) {
        return (root, query, cb) -> {
            if (rating == null) return null;
            return cb.ge(root.get("rating"), rating);
        };
    }

    public static Specification<Product> filterByBrands(Set<String> brands) {
        return (root, query, cb) -> {
            if (brands == null || brands.isEmpty()) return null;
            return root.get("brand").get("slug").in(brands);
        };
    }


    public static Specification<Product> sortProductByStrategy(ProductSortEnum productSortEnum) {
        return (root, query, cb) -> {
            if (productSortEnum != null && PRODUCT_SORT_LOGICS.containsKey(productSortEnum)) {
                query.orderBy(PRODUCT_SORT_LOGICS.get(productSortEnum).apply(root, cb));
            }
            return null;
        };
    }

}
