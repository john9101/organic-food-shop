package com.spring.project.organicfoodshop.service.mapper;

import com.spring.project.organicfoodshop.domain.Product;
import com.spring.project.organicfoodshop.domain.request.management.product.CreateProductRequest;
import com.spring.project.organicfoodshop.domain.response.management.product.CreatedProductResponse;
import com.spring.project.organicfoodshop.domain.response.common.product.ProductItemResponse;
import com.spring.project.organicfoodshop.domain.response.common.product.GotDetailInfoProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    Product toProduct(CreateProductRequest createProductRequest);

    CreatedProductResponse toCreatedProductResponse(Product product);

    ProductItemResponse toGotProductItemContentResponse(Product product);

    GotDetailInfoProductResponse toGotDetailInfoProductResponse(Product product);
}