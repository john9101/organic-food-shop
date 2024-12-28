package com.spring.project.organicfoodshop.controller;

import com.spring.project.organicfoodshop.domain.Cart;
import com.spring.project.organicfoodshop.domain.Order;
import com.spring.project.organicfoodshop.domain.OrderItem;
import com.spring.project.organicfoodshop.domain.User;
import com.spring.project.organicfoodshop.domain.request.common.order.PlaceOrderRequest;
import com.spring.project.organicfoodshop.domain.response.common.order.PlacedOrderResponse;
import com.spring.project.organicfoodshop.service.OrderService;
import com.spring.project.organicfoodshop.service.UserService;
import com.spring.project.organicfoodshop.service.VNPayService;
import com.spring.project.organicfoodshop.service.mapper.OrderMapper;
import com.spring.project.organicfoodshop.util.CartUtil;
import com.spring.project.organicfoodshop.util.ProductUtil;
import com.spring.project.organicfoodshop.util.RequestUtil;
import com.spring.project.organicfoodshop.util.SecurityUtil;
import com.spring.project.organicfoodshop.util.annotation.ApiRequestMessage;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final VNPayService vnPayService;
    private final UserService userService;

    @PostMapping("/current")
    @ApiRequestMessage("Call place order API request")
    public ResponseEntity<PlacedOrderResponse> placeOrder(@RequestBody PlaceOrderRequest request, HttpServletRequest httpServletRequest) {
        Cart currentCart = CartUtil.getCurrentCart(userService);
        User user = currentCart.getUser();
        Order order = OrderMapper.INSTANCE.toOrder(request);
        order.setUser(user);
        Set<OrderItem> orderItems = currentCart.getCartItems().stream().map(cartItem -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setPrice(ProductUtil.getProductPrice(cartItem.getProduct()));
            orderItem.setQuantity(cartItem.getQuantity());
            return orderItem;
        }).collect(Collectors.toSet());
        order.setOrderItems(orderItems);
        order = orderService.handleSaveOrder(order);
        String ipAddress = RequestUtil.getIpddress(httpServletRequest);
        PlacedOrderResponse response = processPlaceOrder(order, ipAddress);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    private PlacedOrderResponse processPlaceOrder(Order order, String ipAddress) {
        PlacedOrderResponse.PlacedOrderResponseBuilder builder = PlacedOrderResponse.builder();
        builder.id(order.getId()).paymentMethod(order.getPaymentMethod());
        switch (order.getPaymentMethod()) {
            case VNPAY:
                String paymentUrl = vnPayService.generatePaymentUrl(order, ipAddress);
                builder.payementUrl(paymentUrl);
                break;
        }
        return builder.build();
    }
}
