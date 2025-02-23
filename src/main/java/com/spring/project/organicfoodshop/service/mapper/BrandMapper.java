package com.spring.project.organicfoodshop.service.mapper;

import com.spring.project.organicfoodshop.domain.Brand;
import com.spring.project.organicfoodshop.domain.request.management.brand.AddBrandRequest;
import com.spring.project.organicfoodshop.domain.response.management.brand.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BrandMapper {

    BrandMapper INSTANCE = Mappers.getMapper(BrandMapper.class);

    Brand toBrand(AddBrandRequest createBrandRequest);

    GotBrandDetailResponse toGotBrandDetailResponse(Brand brand);

    AddedBrandResponse toAddedBrandResponse(Brand brand);

    EditedBrandResponse toEditedBrandResponse(Brand brand);
    
    DeletedBrandResponse toDeletedBrandResponse(Brand brand);

    RecoveredBrandResponse toRecoveredBrandResponse(Brand brand);

    DisplayedBrandResponse toDisplayedBrandResponse(Brand brand);

    List<GotAllBrandResponse.Item> toAllBrandItems(List<Brand> brands);
}
