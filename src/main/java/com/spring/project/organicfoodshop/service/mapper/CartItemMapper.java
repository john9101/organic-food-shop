package com.spring.project.organicfoodshop.service.mapper;

import com.spring.project.organicfoodshop.domain.CartItem;
import com.spring.project.organicfoodshop.domain.request.common.cart.AddItemToCartRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CartItemMapper {
    CartItemMapper INSTANCE = Mappers.getMapper(CartItemMapper.class);

    CartItem toCartItem(AddItemToCartRequest addItemToCartRequest);


}
