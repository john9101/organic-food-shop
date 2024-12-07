package com.spring.project.organicfoodshop.service;

import com.spring.project.organicfoodshop.domain.Brand;
import com.spring.project.organicfoodshop.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BrandService {
    private final BrandRepository brandRepository;

    public Brand handleSaveBrand(Brand brand) {
        return brandRepository.save(brand);
    }

    public boolean isExistsBrandByName(String name) {
        return brandRepository.existsByName(name);
    }

    public Optional<Brand> getBrandById(Long id) {
        return brandRepository.findById(id);
    }
}
