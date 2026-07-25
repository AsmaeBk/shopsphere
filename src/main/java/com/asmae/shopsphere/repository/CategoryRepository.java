package com.asmae.shopsphere.repository;

import com.asmae.shopsphere.model.Category;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    
}
