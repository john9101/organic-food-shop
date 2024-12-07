package com.spring.project.organicfoodshop.service.specification;

import com.spring.project.organicfoodshop.domain.Product;
import org.springframework.data.jpa.domain.Specification;

import java.util.Set;

public class ProductSpecification {

    public static Specification<Product> filterByPriceRange(Double minPrice, Double maxPrice) {
        return (root, query, criteriaBuilder) -> {
            if (minPrice != null && maxPrice != null) {
                return criteriaBuilder.between(root.get("priorityPrice"), minPrice, maxPrice);
            } else if (minPrice != null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("priorityPrice"), minPrice);
            }else if (maxPrice != null) {
                return criteriaBuilder.lessThanOrEqualTo(root.get("priorityPrice"), maxPrice);
            }
            return null;
        };
    }

    public static Specification<Product> filterByRating(Double rating) {
        return (root, query, criteriaBuilder) ->
                rating != null ?  criteriaBuilder.greaterThanOrEqualTo(root.get("rating"), rating) : null;
    }

    public static Specification<Product> filterByBrand(Set<Long> brandIds) {
        return (root, query, criteriaBuilder) ->
                brandIds != null && !brandIds.isEmpty() ?  root.get("brand").get("id").in(brandIds) : null;
    }

}
