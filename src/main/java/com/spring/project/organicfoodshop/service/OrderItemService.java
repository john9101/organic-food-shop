package com.spring.project.organicfoodshop.service;

import com.spring.project.organicfoodshop.domain.OrderItem;
import com.spring.project.organicfoodshop.repository.OrderItemRepository;
import com.spring.project.organicfoodshop.util.FormatterUtil;
import com.spring.project.organicfoodshop.util.constant.ModuleEnum;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;

    public OrderItem getOrderItemById(Long id) {
        return orderItemRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(FormatterUtil.formateExistExceptionMessage("id", id, ModuleEnum.ORDER_ITEM)));
    }
}
