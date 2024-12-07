package com.spring.project.organicfoodshop.service;

import com.spring.project.organicfoodshop.domain.Category;
import com.spring.project.organicfoodshop.domain.Product;
import com.spring.project.organicfoodshop.repository.CategoryRepository;
import com.spring.project.organicfoodshop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public Page<Product> getAllProductsOfCategoryWithPageable(String slug, Pageable pageable) {
        Category category = categoryRepository.findBySlug(slug);
        return productRepository.findAllByCategoriesIn(Collections.singleton(category), pageable);
    }

    public Product handleSaveProduct(Product product) {
        return productRepository.save(product);
    }

    public Product getProductBySlug(String slug) {
        return productRepository.findBySlug(slug);
    }

    public Product getProductById(Long id) {
        return productRepository.findByIdOrThrow(id);
    }

    public boolean isExistsProductByName(String name) {
        return productRepository.existsByName(name);
    }
}
