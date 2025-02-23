package com.spring.project.organicfoodshop.service;

import com.spring.project.organicfoodshop.domain.Order;
import com.spring.project.organicfoodshop.repository.OrderRepository;
import com.spring.project.organicfoodshop.util.FormatterUtil;
import com.spring.project.organicfoodshop.util.constant.ModuleEnum;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public Order handleSaveOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order getOrderById(UUID id) {
        return orderRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(FormatterUtil.formateExistExceptionMessage("id", id, ModuleEnum.ORDER)));
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public void handleDeleteOrderById(UUID id) {
        orderRepository.deleteById(id);
    }
}
