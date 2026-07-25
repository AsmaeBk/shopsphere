package com.asmae.shopsphere.model;

import java.math.BigDecimal;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ProductRequest {

    @NotBlank
    private String name;

    @Length(max = 1000, message = "The text must not exceed 1000 characters")
    private String description;

    @Positive
    private BigDecimal price;

    @Min(0)
    private Integer stockQuantity;

    private Long categoryId;
    
}
