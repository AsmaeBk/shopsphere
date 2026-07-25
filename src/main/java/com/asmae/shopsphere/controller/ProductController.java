package com.asmae.shopsphere.controller;

import org.springframework.web.bind.annotation.RestController;

import com.asmae.shopsphere.exception.ProductNotFoundException;
import com.asmae.shopsphere.model.Product;
import com.asmae.shopsphere.model.ProductRequest;
import com.asmae.shopsphere.model.ProductResponse;
import com.asmae.shopsphere.service.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/products")
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductRequest product ) {   
        return ResponseEntity.ok(productService.createProduct(product));
    }
    
    @GetMapping("/products")
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {
        
        ProductResponse product = productService.getProductById(id);

        return ResponseEntity.ok(product);
    }
    
    @PutMapping("/products/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id,@Valid @RequestBody ProductRequest product) {

        ProductResponse updatedProduct = productService.updateProduct(id, product);
        
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/products-by-page")
    public Page<ProductResponse> getProducts(Pageable pageable ) {
            return productService.getProducts(pageable);
    }

    @GetMapping("/products/search")
    public ResponseEntity<List<ProductResponse>> searchProductsByName(@RequestParam String name, Pageable pageable) {
        List<ProductResponse> searchedProduct = productService.searchByName(name, pageable);
        return ResponseEntity.ok(searchedProduct);
    }
    
}