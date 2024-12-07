package com.spring.project.organicfoodshop.controller;

import com.spring.project.organicfoodshop.domain.Brand;
import com.spring.project.organicfoodshop.domain.request.management.brand.CreateBrandRequest;
import com.spring.project.organicfoodshop.domain.response.management.brand.CreatedBrandResponse;
import com.spring.project.organicfoodshop.service.BrandService;
import com.spring.project.organicfoodshop.service.mapper.BrandMapper;
import com.spring.project.organicfoodshop.util.annotation.ApiRequestMessage;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/brands")
@RequiredArgsConstructor
public class BrandController {
    private final BrandService brandService;

    @PostMapping
    @ApiRequestMessage("Call create brand API request")
    public ResponseEntity<CreatedBrandResponse> createBrand(@Valid @RequestBody CreateBrandRequest createBrandRequest) {
        Brand brand = BrandMapper.INSTANCE.toBrand(createBrandRequest);
        brand = brandService.handleSaveBrand(brand);
        CreatedBrandResponse createdBrandResponse = BrandMapper.INSTANCE.toCreatedBrandResponse(brand);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBrandResponse);
    }
}
