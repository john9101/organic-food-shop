package com.spring.project.organicfoodshop.controller;

import com.spring.project.organicfoodshop.domain.Cart;
import com.spring.project.organicfoodshop.domain.CartItem;
import com.spring.project.organicfoodshop.domain.Product;
import com.spring.project.organicfoodshop.domain.User;
import com.spring.project.organicfoodshop.domain.request.common.cart.AddToCartRequest;
import com.spring.project.organicfoodshop.domain.response.common.cart.IntrospectedOrAddedToCartResponse;
import com.spring.project.organicfoodshop.domain.response.common.cart.ExtractedCartItemResponse;
import com.spring.project.organicfoodshop.service.CartService;
import com.spring.project.organicfoodshop.service.ProductService;
import com.spring.project.organicfoodshop.service.UserService;
import com.spring.project.organicfoodshop.service.mapper.CartItemMapper;
import com.spring.project.organicfoodshop.util.SecurityUtil;
import com.spring.project.organicfoodshop.util.annotation.ApiRequestMessage;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/carts")
public class CartController {
    private final CartService cartService;
    private final ProductService productService;
    private final UserService userService;

    @PostMapping
    @ApiRequestMessage("Call add to cart API request")
    public ResponseEntity<IntrospectedOrAddedToCartResponse> addToCart(@Valid @RequestBody AddToCartRequest addToCartRequest) {
        CartItem initCartItem = CartItemMapper.INSTANCE.toCartItem(addToCartRequest);
        Product product = productService.getProductById(addToCartRequest.getProductId());
        Cart cart = getUserCart();
        Set<CartItem> cartItems = cart.getCartItems();
        Optional<CartItem> optionalCartItem = cartItems.stream()
                .filter(cartItem -> Objects.equals(cartItem.getProduct().getId(), product.getId()))
                .findFirst();
        if (optionalCartItem.isPresent()) {
            initCartItem = optionalCartItem.get();
            initCartItem.setQuantity(initCartItem.getQuantity() + addToCartRequest.getQuantity());
        }else {
            initCartItem.setProduct(product);
            initCartItem.setCart(cart);
            cartItems.add(initCartItem);
        }
        cart = cartService.handleSaveCart(cart);
        return ResponseEntity.status(HttpStatus.CREATED).body(getIntrospectedOrAddedToCartResponse(cart));
    }

    @GetMapping("/introspect")
    @ApiRequestMessage("Call introspect cart API request")
    public ResponseEntity<IntrospectedOrAddedToCartResponse> introspectCart() {
        Cart cart = getUserCart();
        return ResponseEntity.ok(getIntrospectedOrAddedToCartResponse(cart));
    }

    private Cart getUserCart(){
        String email = SecurityUtil.getCurrentUserPrincipal().orElse(null);
        User user = userService.getUserByEmail(email, false);
        return user.getCart();
    }

    private IntrospectedOrAddedToCartResponse getIntrospectedOrAddedToCartResponse(Cart cart) {
        Set<ExtractedCartItemResponse> cartItemInfos = cart.getCartItems().stream()
                .map(cartItem -> ExtractedCartItemResponse.builder()
                        .productId(cartItem.getProduct().getId())
                        .productName(cartItem.getProduct().getName())
                        .quantity(cartItem.getQuantity())
                        .productSlug(cartItem.getProduct().getSlug())
                        .productThumbnail(cartItem.getProduct().getImageUrls().stream().findFirst().orElse(null))
                        .build())
                .collect(Collectors.toSet());
        return IntrospectedOrAddedToCartResponse.builder().cartId(cart.getId()).cartItemInfos(cartItemInfos).build();
    }
}
