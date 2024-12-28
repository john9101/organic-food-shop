package com.spring.project.organicfoodshop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.project.organicfoodshop.domain.Category;
import com.spring.project.organicfoodshop.domain.Product;
import com.spring.project.organicfoodshop.domain.request.common.pagination.PagingRequest;
import com.spring.project.organicfoodshop.domain.request.common.product.QueryProductsRequest;
import com.spring.project.organicfoodshop.domain.request.management.category.CreateCategoryRequest;
import com.spring.project.organicfoodshop.domain.response.common.pagination.PagedResponse;
import com.spring.project.organicfoodshop.domain.response.management.category.CreatedCategoryResponse;
import com.spring.project.organicfoodshop.service.CategoryService;
import com.spring.project.organicfoodshop.service.ProductService;
import com.spring.project.organicfoodshop.service.mapper.CategoryMapper;
import com.spring.project.organicfoodshop.service.mapper.ProductMapper;
import com.spring.project.organicfoodshop.service.specification.ProductSpecification;
import com.spring.project.organicfoodshop.util.EnumUtil;
import com.spring.project.organicfoodshop.util.annotation.ApiRequestMessage;
import com.spring.project.organicfoodshop.util.constant.ProductSortEnum;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final ObjectMapper objectMapper;
    private final ProductService productService;

    @PostMapping
    @ApiRequestMessage("Call create category API request")
    public ResponseEntity<CreatedCategoryResponse> createCategory(@Valid @RequestBody CreateCategoryRequest createCategoryRequest) {
        Category category = CategoryMapper.INSTANCE.toCategory(createCategoryRequest);
        category = categoryService.handleSaveCategory(category);
        CreatedCategoryResponse createdCategoryResponse = CategoryMapper.INSTANCE.toCreatedCategoryResponse(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategoryResponse);
    }

    @GetMapping("/{slug}/products")
    @ApiRequestMessage("Call get products of category API request")
    public ResponseEntity<PagedResponse<PagedResponse.ProductItem>> getProductsOfCategory(
            @RequestParam(defaultValue = "1", required = false) int page,
            @RequestParam(defaultValue = "9", required = false) int size,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) Map<String, Object> queryStringFilter,
            @PathVariable String slug) {
        QueryProductsRequest queryProductsRequest = objectMapper.convertValue(queryStringFilter, QueryProductsRequest.class);
        ProductSortEnum productSort = EnumUtil.getEnumByAttributeValue(ProductSortEnum.class, sort, ProductSortEnum::getStrategy);
        Set<String> brands = queryProductsRequest.getBrands();
        Double minPrice = queryProductsRequest.getMinPrice();
        Double maxPrice = queryProductsRequest.getMaxPrice();
        Double rating = queryProductsRequest.getRating();

        Pageable pageable = PageRequest.of(page - 1, size);
        Specification<Product> productSpecification = Specification.where(ProductSpecification.filterByBrands(brands))
                .and(ProductSpecification.filterByRating(rating))
                .and(ProductSpecification.filterByPriceRange(minPrice, maxPrice))
                .and(ProductSpecification.sortProductByStrategy(productSort));

        Page<Product> productPage = productService.getProductsOfCategory(slug, pageable, productSpecification);
        List<PagedResponse.ProductItem> productItems = ProductMapper.INSTANCE.toProductItems(productPage.getContent());
        PagedResponse<PagedResponse.ProductItem> productPagingResponse = PagedResponse.<PagedResponse.ProductItem>builder()
                .totalPages(productPage.getTotalPages())
                .totalItems(productPage.getNumberOfElements())
                .items(productItems)
                .currentPage(page)
                .build();
        return ResponseEntity.ok(productPagingResponse);
    }
}
