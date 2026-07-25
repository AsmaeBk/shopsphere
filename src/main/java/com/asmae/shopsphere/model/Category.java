package com.asmae.shopsphere.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class Category {
    
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 @NotBlank
 private String name;

 @Length(max = 1000, message = "The text must not exceed 1000 characters")
 private String description;

 @OneToMany(mappedBy = "category")
 private List<Product> products  = new ArrayList<>();

}
