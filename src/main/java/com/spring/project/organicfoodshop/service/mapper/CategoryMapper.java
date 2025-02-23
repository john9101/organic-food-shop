package com.spring.project.organicfoodshop.service.mapper;

import com.spring.project.organicfoodshop.domain.Category;
import com.spring.project.organicfoodshop.domain.request.management.category.AddCategoryRequest;
import com.spring.project.organicfoodshop.domain.response.management.category.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    Category toCategory(AddCategoryRequest createCategoryRequest);

    AddedCategoryResponse toAddedCategoryResponse(Category category);

    EditedCategoryResponse toEditedCategoryResponse(Category category);

    DeletedCategoryResponse toDeletedCategoryResponse(Category category);

    RecoveredCategoryResponse toRecoveredCategoryResponse(Category category);

    DisplayedCategoryResponse toDisplayedCategoryResponse(Category category);
    List<GotAllCatgoriesResponse.Item> toAllCategoryItems(List<Category> categories);

    GotCategoryDetailResponse toGotCategoryDetailResponse(Category category);

}
