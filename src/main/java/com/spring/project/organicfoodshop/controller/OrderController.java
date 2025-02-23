package com.spring.project.organicfoodshop.controller;

import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.spring.project.organicfoodshop.domain.*;
import com.spring.project.organicfoodshop.domain.request.common.order.PlaceOrderRequest;
import com.spring.project.organicfoodshop.domain.request.management.order.AddOrderRequest;
import com.spring.project.organicfoodshop.domain.request.management.order.EditOrderRequest;
import com.spring.project.organicfoodshop.domain.response.common.order.PlacedOrderResponse;
import com.spring.project.organicfoodshop.domain.response.management.order.*;
import com.spring.project.organicfoodshop.service.*;
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
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final VNPayService vnPayService;
    private final UserService userService;
    private final VoucherService voucherService;
    private final ProductService productService;
    private final PaypalService paypalService;


    @PostMapping("/current")
    @ApiRequestMessage("Call place order API request")
    public ResponseEntity<PlacedOrderResponse> placeOrder(@RequestBody PlaceOrderRequest request, HttpServletRequest httpServletRequest) throws PayPalRESTException {
        Cart currentCart = CartUtil.getCurrentCart(userService);
        User user = currentCart.getUser();
        Order order = OrderMapper.INSTANCE.toOrder(request);
        order.setUser(user);

        Set<OrderItem> orderItems = new HashSet<>();
        for (CartItem cartItem : currentCart.getCartItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setPrice(ProductUtil.getProductPrice(cartItem.getProduct()));
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setOrder(order);
            orderItems.add(orderItem);
        }

        order.setOrderItems(orderItems);
        order = orderService.handleSaveOrder(order);
        String ipAddress = RequestUtil.getIpddress(httpServletRequest);
        PlacedOrderResponse response = processPlaceOrder(order, ipAddress);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @ApiRequestMessage("Call get all orders API request")
    public ResponseEntity<GotAllOrdersResponse> getAllOrders(){
        List<Order> orders = orderService.getAllOrders();
        List<GotAllOrdersResponse.Item> items = new ArrayList<>();
        for (Order order : orders) {
            GotAllOrdersResponse.Item item = GotAllOrdersResponse.Item.builder()
                    .id(order.getId())
                    .paymentMethod(order.getPaymentMethod())
                    .recipientFullName(order.getRecipientFullName())
                    .recipientPhone(order.getRecipientPhone())
                    .customerId(order.getUser().getId())
                    .transactionStatus(order.getTransactionStatus())
                    .orderStatus(order.getOrderStatus())
                    .totalPrice(order.getTotalPrice())
                    .createdAt(order.getCreatedAt())
                    .build();
            items.add(item);
        }
        GotAllOrdersResponse response = GotAllOrdersResponse.builder().items(items).build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{orderId}")
    @ApiRequestMessage("Call delete order API request")
    public ResponseEntity<DeletedOrderResponse> deleteOrder(@PathVariable UUID orderId){
        orderService.handleDeleteOrderById(orderId);
        DeletedOrderResponse response = DeletedOrderResponse.builder().id(orderId).build();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @ApiRequestMessage("Call add order API request")
    public ResponseEntity<AddedOrderResponse> addOrder(@RequestBody AddOrderRequest request, HttpServletRequest httpServletRequest) {
        Order order = OrderMapper.INSTANCE.toOrder(request);
        User customer = userService.getUserById(request.getCustomerId());
        order.setUser(customer);
        customer.getOrders().add(order);
        if (request.getVoucherId() != null) {
            Voucher voucher = voucherService.getVoucherById(request.getVoucherId());
            order.setVoucher(voucher);
        }

        Set<OrderItem> orderItems = new HashSet<>();
        for (AddOrderRequest.Item item : request.getItems()) {
            Product product = productService.getProductById(item.getProductId());
            OrderItem orderItem = new OrderItem();
            orderItem.setPrice(ProductUtil.getProductPrice(product));
            orderItem.setQuantity(item.getQuantity());
            orderItem.setProduct(product);
            orderItem.setOrder(order);
            orderItems.add(orderItem);
        }

        order.setOrderItems(orderItems);
        order = orderService.handleSaveOrder(order);
        String ipAddress = RequestUtil.getIpddress(httpServletRequest);
        AddedOrderResponse response = processAddOrder(order, ipAddress);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/{orderId}")
    @ApiRequestMessage("Call edit order API request")
    public ResponseEntity<EditedOrderResponse> editOrder(@PathVariable UUID orderId, @RequestBody EditOrderRequest request) {
        Order order = orderService.getOrderById(orderId);

        order.setPaymentMethod(request.getPaymentMethod());
        order.setRecipientFullName(request.getRecipientFullName());
        order.setRecipientPhone(request.getRecipientPhone());
        order.setRecipientEmail(request.getRecipientEmail());
        order.setRecipientSpecificPlace(request.getRecipientSpecificPlace());
        order.setTransactionStatus(request.getTransactionStatus());
        order.setOrderStatus(request.getOrderStatus());
        order.setProvince(request.getProvince());
        order.setDistrict(request.getDistrict());
        order.setCommune(request.getCommune());
        order.setNote(request.getNote());


        order = orderService.handleSaveOrder(order);
        EditedOrderResponse response = OrderMapper.INSTANCE.toEditedOrderResponse(order);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{orderId}")
    @ApiRequestMessage("Call get order detail API request")
    public ResponseEntity<GotOrderDetailResponse> getOrderDetail(@PathVariable UUID orderId) {
        Order order = orderService.getOrderById(orderId);
        GotOrderDetailResponse response = OrderMapper.INSTANCE.toGotOrderDetailResponse(order);
        response.setCustomerId(order.getUser().getId());
        response.setCustomerFullName(order.getUser().getFullName());
        if (order.getVoucher() != null) {
            Voucher voucher = voucherService.getVoucherById(order.getUser().getId());
            response.setVoucherId(voucher.getId());
            response.setVoucherTitle(voucher.getCode());
        }

        List<GotOrderDetailResponse.Item> items = new ArrayList<>();
        for (OrderItem orderItem : order.getOrderItems()) {
            GotOrderDetailResponse.Item item = GotOrderDetailResponse.Item.builder()
                    .id(orderItem.getId())
                    .quantity(orderItem.getQuantity())
                    .price(orderItem.getPrice())
                    .productTitle(orderItem.getProduct().getTitle())
                    .productId(orderItem.getProduct().getId())
                    .build();
            items.add(item);
        }
        response.setItems(items);
        return ResponseEntity.ok(response);
    }

    private PlacedOrderResponse processPlaceOrder(Order order, String ipAddress) throws PayPalRESTException {
//        PlacedOrderResponse.= PlacedOrderResponse.builder();
//        builder.id(order.getId()).paymentMethod(order.getPaymentMethod());

        return PlacedOrderResponse.builder()
                .id(order.getId())
                .paymentMethod(order.getPaymentMethod())
                .paymentUrl(processGeneratePaymentUrl(order, ipAddress))
                .build();
    }


    private AddedOrderResponse processAddOrder(Order order, String ipAddress) {
        AddedOrderResponse response = OrderMapper.INSTANCE.toAddedOrderResponse(order);
        response.setCustomerId(order.getUser().getId());
        switch (order.getPaymentMethod()) {
            case VNPAY:
                String paymentUrl = vnPayService.generatePaymentUrl(order, ipAddress);
                response.setPaymentUrl(paymentUrl);
                break;
        }
        return response;
    }

    private String processGeneratePaymentUrl(Order order, String ipAddress) throws PayPalRESTException {
        String paymentUrl = null;
        switch (order.getPaymentMethod()) {
            case VNPAY:
                paymentUrl = vnPayService.generatePaymentUrl(order, ipAddress);
                break;
            case PAYPAL:
                paymentUrl  = paypalService.generatePaymentUrl(order, "sale");
                break;
        }
        return paymentUrl;
    }

}
