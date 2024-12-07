package com.spring.project.organicfoodshop.service.mapper;

import com.spring.project.organicfoodshop.domain.Category;
import com.spring.project.organicfoodshop.domain.request.management.category.CreateCategoryRequest;
import com.spring.project.organicfoodshop.domain.response.management.category.CreatedCategoryResponse;
import com.spring.project.organicfoodshop.domain.response.common.product.GotDetailInfoProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    Category toCategory(CreateCategoryRequest createCategoryRequest);

    CreatedCategoryResponse toCreatedCategoryResponse(Category category);

    GotDetailInfoProductResponse.ProductCategoryInfo toProductCategoryInfo(Category category);
}
