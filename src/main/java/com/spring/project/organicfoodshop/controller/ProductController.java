package com.spring.project.organicfoodshop.controller;

import com.spring.project.organicfoodshop.domain.*;
import com.spring.project.organicfoodshop.domain.request.management.product.AddProductRequest;
import com.spring.project.organicfoodshop.domain.request.management.product.EditProductRequest;
import com.spring.project.organicfoodshop.domain.response.common.product.GotProductDetailResponse;
import com.spring.project.organicfoodshop.domain.response.management.product.AddedProductResponse;
import com.spring.project.organicfoodshop.domain.response.management.product.DeletedProductResponse;
import com.spring.project.organicfoodshop.domain.response.management.product.EditedProductResponse;
import com.spring.project.organicfoodshop.domain.response.management.product.GotAllProductsResponse;
import com.spring.project.organicfoodshop.service.*;
import com.spring.project.organicfoodshop.service.mapper.CategoryMapper;
import com.spring.project.organicfoodshop.service.mapper.ProductMapper;
import com.spring.project.organicfoodshop.util.annotation.ApiRequestMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final BrandService brandService;
    private final CategoryService categoryService;
    private final CloudinaryService cloudinaryService;

    @PostMapping
    @ApiRequestMessage("Call add product API request")
    public ResponseEntity<AddedProductResponse> addProduct(@RequestBody AddProductRequest request, @RequestParam List<MultipartFile> images) throws IOException {
        Product product = ProductMapper.INSTANCE.toProduct(request);
        Brand brand = brandService.getBrandById(request.getBrandId()).orElse(null);
        Category category = categoryService.getCategoryById(request.getCategoryId());
        product.setBrand(brand);
        product.setCategory(category);
        cloudinaryService.handleUploadProductImage(product, images);
        product = productService.handleSaveProduct(product);
        AddedProductResponse response = ProductMapper.INSTANCE.toAddedProductResponse(product);
        response.setBrandName(product.getBrand().getName());
        response.setCategoryName(product.getCategory().getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/{productId}")
    @ApiRequestMessage("Call edit product API request")
    public ResponseEntity<EditedProductResponse> editProduct(@PathVariable Long productId , @RequestBody EditProductRequest request, @RequestParam(required = false) List<MultipartFile> images) throws IOException {
        Product product = productService.getProductById(productId);
        Brand brand = brandService.getBrandById(request.getBrandId()).orElse(null);
        Category category = categoryService.getCategoryById(request.getCategoryId());
        product.setBrand(brand);
        product.setCategory(category);
        if (images != null && !images.isEmpty()) {
            cloudinaryService.handleRemoveProductImage(product);
            cloudinaryService.handleUploadProductImage(product, images);
        }
        product = productService.handleSaveProduct(product);
        EditedProductResponse response = ProductMapper.INSTANCE.toEditedProductResponse(product);
        response.setBrandName(product.getBrand().getName());
        response.setCategoryName(product.getCategory().getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{productId}")
    @ApiRequestMessage("Call delete product API request")
    public ResponseEntity<DeletedProductResponse> getProduct(@PathVariable("productId") Long productId) throws IOException {
        Product product = productService.getProductById(productId);
        cloudinaryService.handleRemoveProductImage(product);
        productService.handleDeleteProduct(product);
        DeletedProductResponse response = DeletedProductResponse.builder().id(productId).build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{productId}")
    @ApiRequestMessage("Call get product detail API request")
    public ResponseEntity<GotProductDetailResponse> getProductDetail(@PathVariable("productId") Long productId) {
        Product product = productService.getProductById(productId);
        GotProductDetailResponse response = ProductMapper.INSTANCE.toGotProductDetailResponse(product);
        response.setCategoryName(product.getCategory().getName());
        response.setBrandName(product.getBrand().getName());
        response.setMeasurementUnitName(product.getMeasurementUnit().name());
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @ApiRequestMessage("Call get all products API request")
    public ResponseEntity<GotAllProductsResponse> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        List<GotAllProductsResponse.Item> items = ProductMapper.INSTANCE.toAllProductItems(products);
        GotAllProductsResponse response = GotAllProductsResponse.builder().items(items).build();;
        return ResponseEntity.ok(response);
    }

}
