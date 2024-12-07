package com.spring.project.organicfoodshop.service.mapper;

import com.spring.project.organicfoodshop.domain.CartItem;
import com.spring.project.organicfoodshop.domain.request.common.cart.AddToCartRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CartItemMapper {
    CartItemMapper INSTANCE = Mappers.getMapper(CartItemMapper.class);

    public CartItem toCartItem(AddToCartRequest request);

}
