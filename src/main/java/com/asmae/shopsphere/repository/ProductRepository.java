package com.asmae.shopsphere.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asmae.shopsphere.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>  {
    
}
