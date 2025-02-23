package com.spring.project.organicfoodshop.repository;

import com.spring.project.organicfoodshop.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>, BaseRepository<Cart, Long> {

}
