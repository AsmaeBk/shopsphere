package com.asmae.shopsphere.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asmae.shopsphere.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}