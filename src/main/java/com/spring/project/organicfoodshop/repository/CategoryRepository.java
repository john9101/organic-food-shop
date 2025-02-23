package com.spring.project.organicfoodshop.repository;

import com.spring.project.organicfoodshop.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
    Category findByName(String name);
}
