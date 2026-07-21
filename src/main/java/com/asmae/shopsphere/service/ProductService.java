package com.asmae.shopsphere.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.asmae.shopsphere.exception.ProductNotFoundException;
import com.asmae.shopsphere.model.Product;
import com.asmae.shopsphere.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepo ;

    public Product createProduct(Product product) {

        productRepo.save(product);
        
        return product;
    }

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Product getProductById(Long id) {
        return  productRepo.findById(id).orElseThrow(()->  new ProductNotFoundException(id));

    }

    public Product updateProduct(Long id, Product product) {
        
        Product existingProduct = productRepo.findById(id)
        .orElseThrow(() -> new ProductNotFoundException(id));

        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setStockQuantity(product.getStockQuantity());

        return productRepo.save(existingProduct);

    }

}
