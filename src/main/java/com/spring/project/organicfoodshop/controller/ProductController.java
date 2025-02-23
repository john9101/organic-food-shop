package com.spring.project.organicfoodshop.controller;

import com.spring.project.organicfoodshop.domain.*;
import com.spring.project.organicfoodshop.domain.request.management.product.AddProductRequest;
import com.spring.project.organicfoodshop.domain.request.management.product.EditProductRequest;
import com.spring.project.organicfoodshop.domain.response.common.product.GotProductDetailResponse;
import com.spring.project.organicfoodshop.domain.response.common.product.SearchedProductResponse;
import com.spring.project.organicfoodshop.domain.response.management.product.*;
import com.spring.project.organicfoodshop.service.*;
import com.spring.project.organicfoodshop.service.mapper.ProductMapper;
import com.spring.project.organicfoodshop.util.annotation.ApiRequestMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

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
    public ResponseEntity<AddedProductResponse> addProduct(@ModelAttribute AddProductRequest request, @RequestParam("images") List<MultipartFile> images) throws IOException {
        Product product = ProductMapper.INSTANCE.toProduct(request);
        Brand brand = brandService.getBrandById(request.getBrandId()).orElse(null);
        Category category = categoryService.getCategoryById(request.getCategoryId());
        product.setBrand(brand);
        product.setCategory(category);
        cloudinaryService.handleUploadProductImage(product, images);
        product = productService.handleSaveProduct(product);
        AddedProductResponse response = ProductMapper.INSTANCE.toAddedProductResponse(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/{productId}")
    @ApiRequestMessage("Call edit product API request")
    public ResponseEntity<EditedProductResponse> editProduct(@PathVariable Long productId , @ModelAttribute EditProductRequest request, @RequestParam(required = false) List<MultipartFile> images) throws IOException {
        Product product = productService.getProductById(productId);
        Brand brand = brandService.getBrandById(request.getBrandId()).orElse(null);
        Category category = categoryService.getCategoryById(request.getCategoryId());
        product.setBrand(brand);
        product.setCategory(category);
        if (images != null && !images.isEmpty()) {
            cloudinaryService.handleRemoveProductImage(product);
            product.getImages().clear();
            cloudinaryService.handleUploadProductImage(product, images);
        }
        product = productService.handleSaveProduct(product);
        EditedProductResponse response = ProductMapper.INSTANCE.toEditedProductResponse(product);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{productId}")
    @ApiRequestMessage("Call delete product API request")
    public ResponseEntity<DeletedProductResponse> deleteProduct(@PathVariable("productId") Long productId){
        Product product = productService.getProductById(productId);
        product.setIsDeleted(true);
        product = productService.handleSaveProduct(product);
        DeletedProductResponse response = ProductMapper.INSTANCE.toDeletedProductResponse(product);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{productId}/recovery")
    @ApiRequestMessage("Call recover product API request")
    public ResponseEntity<RecoveredProductResponse> recoverProduct(@PathVariable("productId") Long productId){
        Product product = productService.getProductById(productId);
        product.setIsDeleted(false);
        product = productService.handleSaveProduct(product);
        RecoveredProductResponse response = ProductMapper.INSTANCE.toRecoveredProductResponse(product);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{productId}/visibility/{isVisible}")
    @ApiRequestMessage("Call recover product API request")
    public ResponseEntity<DisplayedProductResponse> recoverProduct(@PathVariable("productId") Long productId, @PathVariable("isVisible") Boolean isVisible){
        Product product = productService.getProductById(productId);
        product.setIsVisible(isVisible);
        product = productService.handleSaveProduct(product);
        DisplayedProductResponse response = ProductMapper.INSTANCE.toDisplayedProductResponse(product);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{productId}")
    @ApiRequestMessage("Call get product detail API request")
    public ResponseEntity<GotProductDetailResponse> getProductDetail(@PathVariable("productId") Long productId) {
        Product product = productService.getProductById(productId);
        GotProductDetailResponse response = ProductMapper.INSTANCE.toGotProductDetailResponse(product);
        Category category = product.getCategory();
        Brand brand = product.getBrand();
        response.setCategoryName(category.getName());
        response.setCategoryId(category.getId());
        response.setBrandName(brand.getName());
        response.setBrandId(brand.getId());
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


    @GetMapping("/searches")
    @ApiRequestMessage("Call search product API request")
    public ResponseEntity<SearchedProductResponse> searchProduct(@RequestParam String keyword) {
        List<Product> products = productService.handleSearchProduct(keyword);
        List<SearchedProductResponse.Item> items = ProductMapper.INSTANCE.toSearchedProductItems(products);
        SearchedProductResponse response = SearchedProductResponse.builder().items(items).build();
        return ResponseEntity.ok(response);
    }
}
