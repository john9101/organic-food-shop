package com.spring.project.organicfoodshop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.project.organicfoodshop.domain.Category;
import com.spring.project.organicfoodshop.domain.Product;
import com.spring.project.organicfoodshop.domain.request.common.product.GetProductItemsRequest;
import com.spring.project.organicfoodshop.domain.request.management.category.CreateCategoryRequest;
import com.spring.project.organicfoodshop.domain.response.common.pagination.PagedResponse;
import com.spring.project.organicfoodshop.domain.response.common.product.ProductItemResponse;
import com.spring.project.organicfoodshop.domain.response.management.category.CreatedCategoryResponse;
import com.spring.project.organicfoodshop.domain.response.common.category.CategoryItemResponse;
import com.spring.project.organicfoodshop.service.CategoryService;
import com.spring.project.organicfoodshop.service.ProductService;
import com.spring.project.organicfoodshop.service.mapper.CategoryMapper;
import com.spring.project.organicfoodshop.service.mapper.ProductMapper;
import com.spring.project.organicfoodshop.util.annotation.ApiRequestMessage;
import com.spring.project.organicfoodshop.util.constant.SortTypeEnum;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

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

    @GetMapping("/{slug}")
    @ApiRequestMessage("Call get all products of category API request")
    public ResponseEntity<PagedResponse<ProductItemResponse>> getProductsOfCategoryWithPagination(@RequestParam Map<String, String> queryStringMap, @PathVariable String slug) {
        GetProductItemsRequest getProductItemsRequest = objectMapper.convertValue(queryStringMap, GetProductItemsRequest.class);
        Sort sortStrategy = Sort.unsorted();
        String sortRequest = getProductItemsRequest.getPagingRequest().getSort();
        if (sortRequest.equals(SortTypeEnum.PRICE_ASC.getValue())) {
            sortStrategy = Sort.by("priceUseForSort").ascending();
        }else if (sortRequest.equals(SortTypeEnum.PRICE_DESC.getValue())) {
            sortStrategy = Sort.by("priceUseForSort").descending();
        }

        Pageable productPageable = PageRequest.of(
                getProductItemsRequest.getPagingRequest().getPage() - 1,
                getProductItemsRequest.getPagingRequest().getSize(),
                sortStrategy);
//        Specification<Product> productSpecification = Specification
//                .where(ProductSpecification.filterByPriceRange(getProductsRequest.getMinPrice(), getProductsRequest.getMaxPrice()))
//                .or(ProductSpecification.filterByRating(getProductsRequest.getRating()))
//                .or(ProductSpecification.filterByBrand(getProductsRequest.getBrandIds()));
//        Page<Product> productPage = productService.getAllProductsWithPageableAndSpecification(productPageable, productSpecification);
        Page<Product> productPage = productService.getAllProductsOfCategoryWithPageable(slug,productPageable);
        List<ProductItemResponse> gotProductItems = productPage.getContent().stream().map(ProductMapper.INSTANCE::toGotProductItemContentResponse).toList();
        PagedResponse<ProductItemResponse> productItemPagingResponse = PagedResponse.<ProductItemResponse>builder()
                .totalPages(productPage.getTotalPages())
                .totalItems(productPage.getNumberOfElements())
                .items(gotProductItems)
                .currentPage(getProductItemsRequest.getPagingRequest().getPage())
                .build();
        return ResponseEntity.ok(productItemPagingResponse);
    }


//    @GetMapping
//    @ApiRequestMessage("Call get all categories API request")
//    public ResponseEntity<Set<CategoryItemResponse>> getAllCategoriesClientSide() {
//
//    }

}
