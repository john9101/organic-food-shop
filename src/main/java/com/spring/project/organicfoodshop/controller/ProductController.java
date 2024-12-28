package com.spring.project.organicfoodshop.controller;

import com.spring.project.organicfoodshop.domain.*;
import com.spring.project.organicfoodshop.domain.request.management.product.CreateProductRequest;
import com.spring.project.organicfoodshop.domain.response.common.product.GotProductDetailResponse;
import com.spring.project.organicfoodshop.domain.response.management.product.CreatedProductResponse;
import com.spring.project.organicfoodshop.service.*;
import com.spring.project.organicfoodshop.service.mapper.BrandMapper;
import com.spring.project.organicfoodshop.service.mapper.CategoryMapper;
import com.spring.project.organicfoodshop.service.mapper.ProductMapper;
import com.spring.project.organicfoodshop.util.annotation.ApiRequestMessage;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final BrandService brandService;
    private final CategoryService categoryService;

    @PostMapping
    @ApiRequestMessage("Call create product API request")
    public ResponseEntity<CreatedProductResponse> createProduct(@Valid @RequestBody CreateProductRequest createProductRequest) {
        Product product = ProductMapper.INSTANCE.toProduct(createProductRequest);
        Brand brand = brandService.getBrandById(createProductRequest.getBrandId()).orElse(null);
        Set<Category> categories = categoryService.getAllCategoriesById(createProductRequest.getCategoryIds());
        product.setBrand(brand);
        product.getCategories().addAll(categories);
        product = productService.handleSaveProduct(product);
        CreatedProductResponse createdProductResponse = ProductMapper.INSTANCE.toCreatedProductResponse(product);
        createdProductResponse.setBrandName(product.getBrand().getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProductResponse);
    }

    @GetMapping("/{slug}")
    @ApiRequestMessage("Call get product detail API request")
    public ResponseEntity<GotProductDetailResponse> getProductDetail(@PathVariable("slug") String slug) {
        Product product = productService.getProductBySlug(slug);
        GotProductDetailResponse.BrandInfo brandInfo = BrandMapper.INSTANCE.toBrandInfo(product.getBrand());
        Set<GotProductDetailResponse.CategoryInfo> categoryInfos  = CategoryMapper.INSTANCE.toCategoryInfos(product.getCategories());
        GotProductDetailResponse response = ProductMapper.INSTANCE.toGotProductDetailResponse(product);
        response.setCategoryInfos(categoryInfos);
        response.setBrandInfo(brandInfo);
        return ResponseEntity.ok(response);
    }
}
