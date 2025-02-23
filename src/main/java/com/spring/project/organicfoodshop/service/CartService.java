package com.spring.project.organicfoodshop.service;

import com.spring.project.organicfoodshop.domain.Cart;
import com.spring.project.organicfoodshop.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;

    public Cart handleSaveCart(Cart cart) {
        return cartRepository.save(cart);
    }
}
