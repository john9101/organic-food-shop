package com.spring.project.organicfoodshop.service.mapper;

import com.spring.project.organicfoodshop.domain.Brand;
import com.spring.project.organicfoodshop.domain.request.management.brand.AddBrandRequest;
import com.spring.project.organicfoodshop.domain.response.management.brand.AddedBrandResponse;
import com.spring.project.organicfoodshop.domain.response.management.brand.GotAllBrandResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BrandMapper {

    BrandMapper INSTANCE = Mappers.getMapper(BrandMapper.class);

    Brand toBrand(AddBrandRequest createBrandRequest);

    AddedBrandResponse toAddedBrandResponse(Brand brand);

    List<GotAllBrandResponse.Item> toAllBrandItems(List<Brand> brands);
}
