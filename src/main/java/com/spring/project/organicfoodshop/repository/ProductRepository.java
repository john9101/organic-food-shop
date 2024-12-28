package com.spring.project.organicfoodshop.repository;

import com.spring.project.organicfoodshop.domain.Category;
import com.spring.project.organicfoodshop.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> , JpaSpecificationExecutor<Product>{
    boolean existsByName(String name);
    Page<Product> findProductsByCategories(Set<Category> categories, Pageable pageable);

    Optional<Product> findBySlug(String slug);
//    Product findBySlug(String slug);

    Page<Product> findByCategories(Set<Category> categories, Pageable pageable, Specification<Product> specification);
}
