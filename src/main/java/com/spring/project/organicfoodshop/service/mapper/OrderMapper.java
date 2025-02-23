package com.spring.project.organicfoodshop.service.mapper;

import com.spring.project.organicfoodshop.domain.Order;
import com.spring.project.organicfoodshop.domain.request.common.order.PlaceOrderRequest;
import com.spring.project.organicfoodshop.domain.request.management.order.AddOrderRequest;
import com.spring.project.organicfoodshop.domain.response.management.order.AddedOrderResponse;
import com.spring.project.organicfoodshop.domain.response.management.order.EditedOrderResponse;
import com.spring.project.organicfoodshop.domain.response.management.order.GotOrderDetailResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    Order toOrder(PlaceOrderRequest placeOrderRequest);

    GotOrderDetailResponse toGotOrderDetailResponse(Order order);

    Order toOrder(AddOrderRequest addOrderRequest);

    AddedOrderResponse toAddedOrderResponse(Order order);

    EditedOrderResponse toEditedOrderResponse(Order order);
}
