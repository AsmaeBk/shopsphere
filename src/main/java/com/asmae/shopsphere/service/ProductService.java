package com.asmae.shopsphere.service;

import java.math.BigDecimal;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.asmae.shopsphere.exception.CategoryNotFoundException;
import com.asmae.shopsphere.exception.ProductNotFoundException;
import com.asmae.shopsphere.model.Category;
import com.asmae.shopsphere.model.Product;
import com.asmae.shopsphere.model.ProductRequest;
import com.asmae.shopsphere.model.ProductResponse;
import com.asmae.shopsphere.repository.CategoryRepository;
import com.asmae.shopsphere.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepo ;

    private final CategoryRepository categoryRepository;
    public ProductResponse createProduct( ProductRequest  product) {

        Category cat = categoryRepository.findById(product.getCategoryId())
                            .orElseThrow(()->new CategoryNotFoundException(product.getCategoryId()));

        Product newProduct = Product.builder()
                            .name(product.getName())
                            .description(product.getDescription())
                            .price(product.getPrice())
                            .stockQuantity(product.getStockQuantity())
                            .category(cat)
                            .build();


        Product saved = productRepo.save(newProduct);

        return toResponse(saved);
        
    }

    public List<ProductResponse> getAllProducts() {

        List<Product> products = productRepo.findAll();

        return products.stream()
        .map(prod->toResponse(prod))
        .toList();
    }

    public ProductResponse getProductById(Long id) {
       Product product =  productRepo.findById(id).orElseThrow(()->  new ProductNotFoundException(id));
       return toResponse(product);

    }

    public ProductResponse updateProduct(Long id, ProductRequest product) {
        
        Product existingProduct = productRepo.findById(id)
        .orElseThrow(() -> new ProductNotFoundException(id));
        Category category = categoryRepository.findById(product.getCategoryId()).orElseThrow(()-> new CategoryNotFoundException(product.getCategoryId()));
        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setStockQuantity(product.getStockQuantity());
        existingProduct.setCategory(category);

        Product savedProduct =  productRepo.save(existingProduct);

        return toResponse(savedProduct);

    }

    public void deleteProduct(Long id) {

        Product existingProduct = productRepo.findById(id).orElseThrow(()-> new ProductNotFoundException(id));
        productRepo.delete(existingProduct) ;
    }

    public Page<ProductResponse> getProducts(Pageable pageable) {
        Page<Product> products = productRepo.findAll(pageable);
        return products.map(prod ->this.toResponse(prod));
    }

    // public List<Product> searchByName(String name) {
    //     List<Product> products = productRepo.findProductsByName(name).orElseThrow(()->new ProductNotFoundByNameException(name));

    //     return products;
    // }

    public List<ProductResponse> searchByName(String name, Pageable pageable) {
        List<Product> products =   productRepo.findByNameContainingIgnoreCase(name, pageable);

        return products.stream().map(product->toResponse(product)).toList();

    }

    private ProductResponse toResponse(Product product) {
         return ProductResponse.builder()
            .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stockQuantity(product.getStockQuantity())
                .categoryName(product.getCategory() !=  null ? product.getCategory().getName() : null)
                .build();
    }

    public List<ProductResponse> getProductsByName(String categoryName) {
    
        List<Product> products = productRepo.findAllByCategoryName(categoryName);
        
        return products.stream()
                .map(prod->toResponse(prod))
                .toList();
    }

    public List<ProductResponse> getProductsByPriceRange(BigDecimal min, BigDecimal max) {
        
        List<Product> products = productRepo.findProductsBetweenPrices(min, max);

        return products.stream().map(prod->toResponse(prod)).toList();
    }

    public List<ProductResponse> searcgProductsByCategoryAndName(String categoryName, String productName) {

        List<Product> products = productRepo.searcgProductsByCategoryAndName(categoryName, productName);
        
        return products.stream()
                .map(prod->toResponse(prod))
                .toList();
    }
}
