package com.asmae.shopsphere.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asmae.shopsphere.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{
    
}
