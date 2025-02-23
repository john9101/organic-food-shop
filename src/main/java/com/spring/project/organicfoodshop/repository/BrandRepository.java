package com.spring.project.organicfoodshop.repository;

import com.spring.project.organicfoodshop.domain.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long>, BaseRepository<Brand, Long> {

    boolean existsByName(String name);
}
