package com.asmae.shopsphere.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

    @ManyToOne
    @JoinColumn(name="category_id")
    @JsonBackReference
    private Category category;

    @OneToMany(mappedBy = "product")
    private List<OrderItem> items = new ArrayList<>();
    
}
