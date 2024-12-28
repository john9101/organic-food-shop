package com.spring.project.organicfoodshop.service.mapper;

import com.spring.project.organicfoodshop.domain.Order;
import com.spring.project.organicfoodshop.domain.request.common.order.PlaceOrderRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    Order toOrder(PlaceOrderRequest placeOrderRequest);
}
