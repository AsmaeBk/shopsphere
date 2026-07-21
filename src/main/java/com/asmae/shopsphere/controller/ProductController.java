package com.asmae.shopsphere.controller;

import org.springframework.web.bind.annotation.RestController;

import com.asmae.shopsphere.exception.ProductNotFoundException;
import com.asmae.shopsphere.model.Product;
import com.asmae.shopsphere.service.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product ) {
        Product savedProduct = productService.createProduct(product);
        return ResponseEntity.ok(savedProduct);
    
    }
    
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        
        Product product = productService.getProductById(id);

        return ResponseEntity.ok(product);
    }
    
    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id,@Valid @RequestBody Product product) {

        Product updateddProduct = productService.updateProduct(id, product);
        
        return ResponseEntity.ok(updateddProduct);
    }

}