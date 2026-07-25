package com.asmae.shopsphere.service;

import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.asmae.shopsphere.exception.CategoryNotFoundException;
import com.asmae.shopsphere.exception.ProductNotFoundException;
import com.asmae.shopsphere.model.Category;
import com.asmae.shopsphere.model.Product;
import com.asmae.shopsphere.repository.CategoryRepository;
import com.asmae.shopsphere.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepo ;

    private final CategoryRepository categoryRepository;
    public Product createProduct(Product product) {

        Category cat = categoryRepository.findById(product.getCategory().getId())
                            .orElseThrow(()->new CategoryNotFoundException(product.getCategory().getId()));

        product.setCategory(cat);
        
        return productRepo.save(product);
        
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

    public void deleteProduct(Long id) {

        Product existingProduct = productRepo.findById(id).orElseThrow(()-> new ProductNotFoundException(id));
        productRepo.delete(existingProduct) ;
    }

    public Page<Product> getProducts(Pageable pageable) {
       
        return productRepo.findAll(pageable);
    }

    // public List<Product> searchByName(String name) {
    //     List<Product> products = productRepo.findProductsByName(name).orElseThrow(()->new ProductNotFoundByNameException(name));

    //     return products;
    // }

    public List<Product> searchByName(String name, Pageable pageable) {
        return productRepo.findByNameContainingIgnoreCase(name, pageable);

    }


}
