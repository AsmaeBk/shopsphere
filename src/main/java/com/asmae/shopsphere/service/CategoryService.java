package com.asmae.shopsphere.service;

import java.util.List;
import com.asmae.shopsphere.model.Category;

import org.springframework.stereotype.Service;

import com.asmae.shopsphere.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository  categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }
    
}
