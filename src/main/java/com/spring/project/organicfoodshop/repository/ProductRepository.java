package com.spring.project.organicfoodshop.repository;

import com.spring.project.organicfoodshop.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> , JpaSpecificationExecutor<Product>{
    boolean existsByName(String name);

    List<Product> findByNameOrTitleContaining(String name, String title);
}
