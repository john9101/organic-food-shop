package com.spring.project.organicfoodshop.controller;

import com.spring.project.organicfoodshop.domain.Cart;
import com.spring.project.organicfoodshop.domain.CartItem;
import com.spring.project.organicfoodshop.domain.Product;
import com.spring.project.organicfoodshop.domain.User;
import com.spring.project.organicfoodshop.domain.request.common.cart.AddToCartRequest;
import com.spring.project.organicfoodshop.domain.response.common.cart.AddedToCartResponse;
import com.spring.project.organicfoodshop.service.CartService;
import com.spring.project.organicfoodshop.service.ProductService;
import com.spring.project.organicfoodshop.service.UserService;
import com.spring.project.organicfoodshop.service.mapper.CartItemMapper;
import com.spring.project.organicfoodshop.service.mapper.CartMapper;
import com.spring.project.organicfoodshop.util.SecurityUtil;
import com.spring.project.organicfoodshop.util.annotation.ApiRequestMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/carts")
public class CartController {
    private final CartService cartService;
    private final ProductService productService;
    private final UserService userService;

    @PostMapping
    @ApiRequestMessage("Call add to cart API request")
    public ResponseEntity<AddedToCartResponse> addToCart(@RequestBody AddToCartRequest addToCartRequest) {
        CartItem cartItem = CartItemMapper.INSTANCE.toCartItem(addToCartRequest);
        Product product = productService.getProductById(addToCartRequest.getProductId());
        String email = SecurityUtil.getCurrentUserPrincipal().orElse(null);
        User user = userService.getUserByUsername(email).orElseThrow();
        Cart cart = user.getCart();
        Set<CartItem> cartItems = cart.getCartItems();

        Optional<CartItem> optionalCartItem = cartItems.stream()
                .filter(item -> item.getProduct().getId().equals(product.getId())).findFirst();
        if (optionalCartItem.isPresent()) {
            cartItem = optionalCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + addToCartRequest.getQuantity());
        }else {
            cartItem.setProduct(product);
            cartItem.setCart(cart);
            cartItems.add(cartItem);
        }
        cart = cartService.handleSaveCart(cart);
        AddedToCartResponse addedToCartResponse = CartMapper.INSTANCE.toAddedToCartResponse(cart);
        addedToCartResponse.setCartId(cart.getId());
        addedToCartResponse.setCartItemId(cartItem.getId());
        addedToCartResponse.setProductId(product.getId());
        addedToCartResponse.setProductName(product.getName());
        addedToCartResponse.setQuantity(cartItem.getQuantity());
        return ResponseEntity.status(HttpStatus.CREATED).body(addedToCartResponse);
    }
}
