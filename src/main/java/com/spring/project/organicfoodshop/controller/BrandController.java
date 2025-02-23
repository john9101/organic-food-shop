package com.spring.project.organicfoodshop.controller;

import com.spring.project.organicfoodshop.domain.Brand;
import com.spring.project.organicfoodshop.domain.request.management.brand.AddBrandRequest;
import com.spring.project.organicfoodshop.domain.request.management.brand.EditBrandRequest;
import com.spring.project.organicfoodshop.domain.response.management.brand.*;
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

    @GetMapping("/{brandId}")
    @ApiRequestMessage("Call get brand detail API request")
    public ResponseEntity<GotBrandDetailResponse> getBrandDetail(@PathVariable Long brandId) {
        Brand brand = brandService.getBrandById(brandId).orElse(null);
        GotBrandDetailResponse response = BrandMapper.INSTANCE.toGotBrandDetailResponse(brand);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{brandId}")
    @ApiRequestMessage("Call delete brand API request")
    public ResponseEntity<DeletedBrandResponse> deleteBrand(@PathVariable Long brandId) {
        Brand brand = brandService.getBrandById(brandId).orElse(null);
        if (brand != null) {
            brand.setIsDeleted(true);
            brand = brandService.handleSaveBrand(brand);
        }
        DeletedBrandResponse response = BrandMapper.INSTANCE.toDeletedBrandResponse(brand);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{brandId}/recovery")
    @ApiRequestMessage("Call recover brand API request")
    public ResponseEntity<RecoveredBrandResponse> recoverbrand(@PathVariable Long brandId) {
        Brand brand = brandService.getBrandById(brandId).orElse(null);
        if (brand != null) {
            brand.setIsDeleted(false);
            brand = brandService.handleSaveBrand(brand);
        }
        RecoveredBrandResponse response = BrandMapper.INSTANCE.toRecoveredBrandResponse(brand);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{brandId}/visibility/{isVisible}")
    @ApiRequestMessage("Call display brand API request")
    public ResponseEntity<DisplayedBrandResponse> displayBrand(@PathVariable Long brandId, @PathVariable Boolean isVisible) {
        Brand brand = brandService.getBrandById(brandId).orElse(null);
        if (brand != null) {
            brand.setIsVisible(isVisible);
            brand = brandService.handleSaveBrand(brand);
        }
        DisplayedBrandResponse response = BrandMapper.INSTANCE.toDisplayedBrandResponse(brand);
        return ResponseEntity.ok(response);
    }


    @PatchMapping("/{brandId}")
    @ApiRequestMessage("Call edit brand API request")
    public ResponseEntity<EditedBrandResponse> editBrand(@RequestBody EditBrandRequest request, @PathVariable Long brandId) {
        Brand brand = brandService.getBrandById(brandId).orElse(null);
        if (brand != null) {
            brand.setName(request.getName());
            brand.setDescription(request.getDescription());
        }
        EditedBrandResponse response = BrandMapper.INSTANCE.toEditedBrandResponse(brand);
        return ResponseEntity.ok(response);
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
