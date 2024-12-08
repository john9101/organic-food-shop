package com.spring.project.organicfoodshop.event.listener;

import com.spring.project.organicfoodshop.domain.Cart;
import com.spring.project.organicfoodshop.domain.User;
import com.spring.project.organicfoodshop.event.RegisterEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
@RequiredArgsConstructor
public class CartEventListener {

    @EventListener
    public void handleInitializeCartForRegisterEvent(RegisterEvent event) {
        User user = event.getUser();
        Supplier<Cart> cartSupplier = Cart::new;
        user.setCart(cartSupplier.get());
    }
}
