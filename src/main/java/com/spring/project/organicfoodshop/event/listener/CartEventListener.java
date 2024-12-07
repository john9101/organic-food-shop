package com.spring.project.organicfoodshop.event.listener;

import com.spring.project.organicfoodshop.domain.Cart;
import com.spring.project.organicfoodshop.domain.User;
import com.spring.project.organicfoodshop.domain.request.common.cart.InitializeCartRequest;
import com.spring.project.organicfoodshop.event.RegisterEvent;
import com.spring.project.organicfoodshop.service.mapper.CartMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CartEventListener {

    @EventListener
    public void handleInitializeCartForRegisterEvent(RegisterEvent event) {
        User user = event.getUser();
        InitializeCartRequest initializeCartRequest = InitializeCartRequest.builder().user(user).build();
        Cart cart = CartMapper.INSTANCE.toCart(initializeCartRequest);
        user.setCart(cart);
    }
}
