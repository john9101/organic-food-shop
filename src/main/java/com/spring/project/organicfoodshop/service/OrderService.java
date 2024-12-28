package com.spring.project.organicfoodshop.service;

import com.spring.project.organicfoodshop.domain.Order;
import com.spring.project.organicfoodshop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public Order handleSaveOrder(Order order) {
        return orderRepository.save(order);
    }
}
