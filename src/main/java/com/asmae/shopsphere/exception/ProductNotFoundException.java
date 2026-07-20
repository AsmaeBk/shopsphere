package com.asmae.shopsphere.exception;

public class ProductNotFoundException extends RuntimeException {
    
    public ProductNotFoundException(Long productId) {

        super("Product with id " + productId + " not found.");
    }

}
