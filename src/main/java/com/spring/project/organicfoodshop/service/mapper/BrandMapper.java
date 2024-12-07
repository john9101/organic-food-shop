package com.spring.project.organicfoodshop.service.mapper;

import com.spring.project.organicfoodshop.domain.Brand;
import com.spring.project.organicfoodshop.domain.request.management.brand.CreateBrandRequest;
import com.spring.project.organicfoodshop.domain.response.management.brand.CreatedBrandResponse;
import com.spring.project.organicfoodshop.domain.response.common.product.GotDetailInfoProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BrandMapper {

    BrandMapper INSTANCE = Mappers.getMapper(BrandMapper.class);

    Brand toBrand(CreateBrandRequest createBrandRequest);

    CreatedBrandResponse toCreatedBrandResponse(Brand brand);

    GotDetailInfoProductResponse.ProductBrandInfo toProductBrandInfo(Brand brand);
}
