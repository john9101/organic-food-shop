package com.spring.project.organicfoodshop.controller;

import com.spring.project.organicfoodshop.domain.Cart;
import com.spring.project.organicfoodshop.domain.CartItem;
import com.spring.project.organicfoodshop.domain.Product;
import com.spring.project.organicfoodshop.domain.User;
import com.spring.project.organicfoodshop.domain.request.common.cart.AddItemToCartRequest;
import com.spring.project.organicfoodshop.domain.request.common.cart.ChangeQuantityOfItemInCartRequest;
import com.spring.project.organicfoodshop.domain.response.common.cart.AddedItemToCartResponse;
import com.spring.project.organicfoodshop.domain.response.common.cart.ChangedQuantityOfItemInCartResponse;
import com.spring.project.organicfoodshop.domain.response.common.cart.GotCartSummaryResponse;
import com.spring.project.organicfoodshop.domain.response.common.cart.RemovedItemFromCartResponse;
import com.spring.project.organicfoodshop.service.CartItemService;
import com.spring.project.organicfoodshop.service.CartService;
import com.spring.project.organicfoodshop.service.ProductService;
import com.spring.project.organicfoodshop.service.UserService;
import com.spring.project.organicfoodshop.util.CartUtil;
import com.spring.project.organicfoodshop.util.ProductUtil;
import com.spring.project.organicfoodshop.util.SecurityUtil;
import com.spring.project.organicfoodshop.util.annotation.ApiRequestMessage;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/carts")
public class CartController {
    private final CartItemService cartItemService;
    private final ProductService productService;
    private final UserService userService;

    @GetMapping("/current/summary")
    @ApiRequestMessage("Call cart summary API request")
    public ResponseEntity<GotCartSummaryResponse> getCartSummary() {
        Cart currentCart = CartUtil.getCurrentCart(userService);
        Set<GotCartSummaryResponse.Item> items = currentCart.getCartItems().stream()
                .map(this::mapToItem).collect(Collectors.toSet());
        GotCartSummaryResponse gotCartSummaryResponse = GotCartSummaryResponse.builder()
                .id(currentCart.getId())
                .totalCount(items.size())
                .totalPrice(calculateTotalPrice(items))
                .items(items)
                .build();
        return ResponseEntity.ok(gotCartSummaryResponse);
    }

    @PostMapping("/current/items")
    @ApiRequestMessage("Call add an item to cart API request")
    public ResponseEntity<AddedItemToCartResponse> addItemToCart(@Valid @RequestBody AddItemToCartRequest request) {
        Cart currentCart = CartUtil.getCurrentCart(userService);
        Product product = productService.getProductById(request.getProductId());
        CartItem cartItem = cartItemService.getCartItemByCartIdAndProductId(currentCart.getId(), product.getId()).orElse(null);
        if (cartItem != null) {
            cartItem.setQuantity(cartItem.getQuantity() + request.getQuantity());
        }else {
            cartItem = new CartItem();
            cartItem.setCart(currentCart);
            cartItem.setQuantity(request.getQuantity());
            cartItem.setProduct(product);
        }
        cartItem = cartItemService.handleSaveCartItem(cartItem);
        AddedItemToCartResponse response = AddedItemToCartResponse.builder()
                .totalCount(currentCart.getCartItems().size())
                .itemId(cartItem.getId())
                .itemProductId(product.getId())
                .itemTitle(cartItem.getProduct().getTitle())
                .itemQuantity(cartItem.getQuantity())
                .itemPrice(ProductUtil.getProductPrice(product))
                .itemThumbnail(ProductUtil.getProductThumnnail(product))
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/current/items/{itemId}")
    @ApiRequestMessage("Call change quantity of item in cart API request")
    public ResponseEntity<ChangedQuantityOfItemInCartResponse> changeQuantityOfItemInCart(
            @Valid @RequestBody ChangeQuantityOfItemInCartRequest request, @PathVariable Long itemId) {
        CartItem cartItem = cartItemService.getCartItemById(itemId);
        Integer quantity = request.getQuantity();
        cartItem.setQuantity(quantity);
        cartItem = cartItemService.handleSaveCartItem(cartItem);
        ChangedQuantityOfItemInCartResponse response = ChangedQuantityOfItemInCartResponse.builder()
                .itemId(cartItem.getId())
                .itemQuantity(quantity)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/current/items/{itemId}")
    @ApiRequestMessage("Call remove item in cart API request")
    public ResponseEntity<RemovedItemFromCartResponse> removeItemFromCart(@PathVariable Long itemId) {
        CartItem cartItem = cartItemService.getCartItemById(itemId);
        cartItemService.handleDeleteCartItem(cartItem);
        RemovedItemFromCartResponse response = RemovedItemFromCartResponse.builder()
                .totalCount(CartUtil.getCurrentCart(userService).getCartItems().size())
                .itemId(cartItem.getId())
                .build();
        return ResponseEntity.ok(response);
    }

    private GotCartSummaryResponse.Item mapToItem(CartItem cartItem) {
        Product product = cartItem.getProduct();
        Double price = ProductUtil.getProductPrice(product);
        return GotCartSummaryResponse.Item.builder()
                .id(cartItem.getId())
                .quantity(cartItem.getQuantity())
                .price(price)
                .subtotal(calculateSubtotal(cartItem.getQuantity(), price))
                .productId(product.getId())
                .productTitle(product.getTitle())
                .productThumbnail(ProductUtil.getProductThumnnail(product))
                .build();
    }

    private double calculateTotalPrice(Set<GotCartSummaryResponse.Item> items) {
        return items.stream().mapToDouble(GotCartSummaryResponse.Item::getSubtotal).sum();
    }

    private double calculateSubtotal(int quantity, double price) {
        return quantity * price;
    }

}
