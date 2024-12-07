package com.spring.project.organicfoodshop.repository;

import com.spring.project.organicfoodshop.domain.Category;
import com.spring.project.organicfoodshop.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> , JpaSpecificationExecutor<Product>, BaseRepository<Product, Long> {
    boolean existsByName(String name);
    Page<Product> findAllByCategoriesIn(Set<Category> categories, Pageable pageable);
    Product findBySlug(String slug);
}
