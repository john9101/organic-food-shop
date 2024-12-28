package com.spring.project.organicfoodshop.service.mapper;

import com.spring.project.organicfoodshop.domain.Product;
import com.spring.project.organicfoodshop.domain.request.management.product.CreateProductRequest;
import com.spring.project.organicfoodshop.domain.response.common.pagination.PagedResponse;
import com.spring.project.organicfoodshop.domain.response.common.product.GotProductDetailResponse;
import com.spring.project.organicfoodshop.domain.response.management.product.CreatedProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    Product toProduct(CreateProductRequest createProductRequest);

    CreatedProductResponse toCreatedProductResponse(Product product);

    GotProductDetailResponse toGotProductDetailResponse(Product product);

    List<PagedResponse.ProductItem> toProductItems(List<Product> products);
}