package com.spring.project.organicfoodshop.service;

import com.spring.project.organicfoodshop.domain.Category;
import com.spring.project.organicfoodshop.repository.CategoryRepository;
import com.spring.project.organicfoodshop.util.FormatterUtil;
import com.spring.project.organicfoodshop.util.constant.ModuleEnum;
import jakarta.persistence.EntityNotFoundException;
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

    public Category getCategoryById(Long id){
        return categoryRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(FormatterUtil.formateExistExceptionMessage("id", id, ModuleEnum.CATEGORY)));
    }

    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    public Set<Category> getAllCategoriesById(Set<Long> ids) {
        return Set.copyOf(categoryRepository.findAllById(ids));
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
