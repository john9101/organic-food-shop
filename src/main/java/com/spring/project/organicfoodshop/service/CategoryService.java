package com.spring.project.organicfoodshop.service;

import com.spring.project.organicfoodshop.domain.Category;
import com.spring.project.organicfoodshop.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category handleSaveCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category getCategoryByIdOrThrow(Long id) {
        return categoryRepository.findByIdOrThrow(id);
    }

    public Optional<Category> getCategoryById(Long id){
        return categoryRepository.findById(id);
    }

    public Category getCategoryBySlug(String slug) {
        return categoryRepository.findBySlug(slug);
    }

    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    public Set<Category> getAllCategoriesById(Set<Long> ids) {
        return Set.copyOf(categoryRepository.findAllById(ids));
    }
}
