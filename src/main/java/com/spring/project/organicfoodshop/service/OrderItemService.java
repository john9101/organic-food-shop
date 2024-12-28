package com.spring.project.organicfoodshop.service;

import com.spring.project.organicfoodshop.domain.OrderItem;
import com.spring.project.organicfoodshop.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;

    public Set<OrderItem> handleSaveOrderItems(Set<OrderItem> orderItems) {
        return Set.copyOf(orderItemRepository.saveAll(orderItems));
    }
}
