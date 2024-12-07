package com.spring.project.organicfoodshop.service.mapper;

import com.spring.project.organicfoodshop.domain.Cart;
import com.spring.project.organicfoodshop.domain.request.common.cart.InitializeCartRequest;
import com.spring.project.organicfoodshop.domain.response.common.cart.AddedToCartResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CartMapper {
    CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);

    public Cart toCart(InitializeCartRequest initializeCartRequest);

    public AddedToCartResponse toAddedToCartResponse(Cart cart);
}
