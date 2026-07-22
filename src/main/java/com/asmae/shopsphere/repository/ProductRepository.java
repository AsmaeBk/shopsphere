package com.asmae.shopsphere.repository;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;
import com.asmae.shopsphere.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>  {


    // @Query("Select p From Product p WHERE p.name LIKE concat('%',:name,'%')")
    // Optional<List<Product>> findProductsByName(@Param("name") String name);
    List<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
