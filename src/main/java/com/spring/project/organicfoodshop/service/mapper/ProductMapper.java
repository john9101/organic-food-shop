package com.spring.project.organicfoodshop.service.mapper;

import com.spring.project.organicfoodshop.domain.Product;
import com.spring.project.organicfoodshop.domain.request.management.product.AddProductRequest;
import com.spring.project.organicfoodshop.domain.response.common.pagination.PagedResponse;
import com.spring.project.organicfoodshop.domain.response.common.product.GotProductDetailResponse;
import com.spring.project.organicfoodshop.domain.response.common.product.SearchedProductResponse;
import com.spring.project.organicfoodshop.domain.response.management.product.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    Product toProduct(AddProductRequest createProductRequest);

    AddedProductResponse toAddedProductResponse(Product product);

    EditedProductResponse toEditedProductResponse(Product product);

    GotProductDetailResponse toGotProductDetailResponse(Product product);

    List<PagedResponse.ProductItem> toPagedProductItems(List<Product> products);

    List<GotAllProductsResponse.Item> toAllProductItems(List<Product> products);

    DeletedProductResponse toDeletedProductResponse(Product product);

    RecoveredProductResponse toRecoveredProductResponse(Product product);

    DisplayedProductResponse toDisplayedProductResponse(Product product);

    List<SearchedProductResponse.Item> toSearchedProductItems(List<Product> products);
}