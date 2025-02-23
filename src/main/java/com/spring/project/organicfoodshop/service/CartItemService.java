package com.spring.project.organicfoodshop.service;

import com.spring.project.organicfoodshop.domain.CartItem;
import com.spring.project.organicfoodshop.repository.CartItemRepository;
import com.spring.project.organicfoodshop.util.FormatterUtil;
import com.spring.project.organicfoodshop.util.constant.ModuleEnum;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartItemService {
    private final CartItemRepository cartItemRepository;

    public CartItem getCartItemById(Long id) {
        return cartItemRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException(FormatterUtil.formatNotFoundExceptionMessage("id", id, ModuleEnum.CART_ITEM)));
    }

    public CartItem handleSaveCartItem(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    public Optional<CartItem> getCartItemByCartIdAndProductId(Long cartId, Long productId) {
        return cartItemRepository.findByCartIdAndProductId(cartId, productId);
    }


    public void handleDeleteCartItem(CartItem cartItem) {
        cartItemRepository.delete(cartItem);
    }
}
