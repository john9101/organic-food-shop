package com.spring.project.organicfoodshop.controller;

import com.spring.project.organicfoodshop.domain.Brand;
import com.spring.project.organicfoodshop.domain.request.management.brand.AddBrandRequest;
import com.spring.project.organicfoodshop.domain.response.management.brand.AddedBrandResponse;
import com.spring.project.organicfoodshop.domain.response.management.brand.GotAllBrandResponse;
import com.spring.project.organicfoodshop.service.BrandService;
import com.spring.project.organicfoodshop.service.mapper.BrandMapper;
import com.spring.project.organicfoodshop.util.annotation.ApiRequestMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brands")
@RequiredArgsConstructor
public class BrandController {
    private final BrandService brandService;

    @PostMapping
    @ApiRequestMessage("Call create brand API request")
    public ResponseEntity<AddedBrandResponse> addBrand(@RequestBody AddBrandRequest request) {
        Brand brand = BrandMapper.INSTANCE.toBrand(request);
        brand = brandService.handleSaveBrand(brand);
        AddedBrandResponse response = BrandMapper.INSTANCE.toAddedBrandResponse(brand);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @ApiRequestMessage("Call get all brands API request")
    public ResponseEntity<GotAllBrandResponse> getAllBrands() {
        List<Brand> brands = brandService.getAllBrands();
        List<GotAllBrandResponse.Item> items = BrandMapper.INSTANCE.toAllBrandItems(brands);
        GotAllBrandResponse response = GotAllBrandResponse.builder().items(items).build();
        return ResponseEntity.ok(response);
    }

}
