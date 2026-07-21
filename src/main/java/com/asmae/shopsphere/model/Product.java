package com.asmae.shopsphere.model;

import java.math.BigDecimal;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Entity
@Data
@JsonPropertyOrder({
    "id",
    "name",
    "description",
    "price",
    "stockQuantity"
})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @Length(max = 1000, message = "The text must not exceed 1000 characters")
    private String description;

    @Positive
    private BigDecimal price;

    @Min(0)
    private Integer stockQuantity;

    
}
