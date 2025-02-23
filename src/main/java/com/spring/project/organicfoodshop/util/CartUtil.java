package com.spring.project.organicfoodshop.util;

import com.spring.project.organicfoodshop.domain.Cart;
import com.spring.project.organicfoodshop.domain.User;
import com.spring.project.organicfoodshop.service.UserService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CartUtil {

    public static Cart getCurrentCart(UserService userService){
        String email = SecurityUtil.getCurrentUserPrincipal().orElse(null);
        User user = userService.getUserByEmail(email, false);
        return user.getCart();
    }
}
