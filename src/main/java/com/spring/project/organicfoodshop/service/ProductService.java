package com.spring.project.organicfoodshop.service;

import com.spring.project.organicfoodshop.domain.Product;
import com.spring.project.organicfoodshop.repository.ProductRepository;
import com.spring.project.organicfoodshop.util.FormatterUtil;
import com.spring.project.organicfoodshop.util.constant.ModuleEnum;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Page<Product> getProductsOfCategory(Long categoryId, Pageable pageable, Specification<Product> productSpecification) {
        Specification<Product> specification = (root, query, cb) -> cb.equal(root.join("categories").get("id"), categoryId);
        Specification<Product> combineSpecification = Specification.where(specification).and(productSpecification);
        return productRepository.findAll(combineSpecification, pageable);
    }

    public Product handleSaveProduct(Product product) {
        return productRepository.save(product);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(FormatterUtil.formateExistExceptionMessage("id", id, ModuleEnum.PRODUCT)));
    }

    public boolean isExistsProductByName(String name) {
        return productRepository.existsByName(name);
    }

    public boolean isExistsProductById(Long id) {
        return productRepository.existsById(id);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void handleDeleteProduct(Product product) {
        productRepository.delete(product);
    }
}
