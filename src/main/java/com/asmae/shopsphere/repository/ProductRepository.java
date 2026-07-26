package com.asmae.shopsphere.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;
import com.asmae.shopsphere.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>  {


    List<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE LOWER(p.category.name) = LOWER(:name)")
    List<Product> findAllByCategoryName(@Param("name") String categoryName);

    @Query("SELECT p FROM Product p WHERE p.price BETWEEN :min AND :max ")
    List<Product> findProductsBetweenPrices(@Param("min") BigDecimal min, @Param("max")BigDecimal max);

    @Query("SELECT p FROM Product p WHERE LOWER(p.category.name)=LOWER(:category_name) AND LOWER(p.name) LIKE concat('%',LOWER(:product_name),'%')")
    List<Product> searcgProductsByCategoryAndName(@Param("category_name") String categoryName, @Param("product_name")String productName);
}
 