package com.asmae.shopsphere.controller;

import org.springframework.web.bind.annotation.RestController;

import com.asmae.shopsphere.model.Order;
import com.asmae.shopsphere.model.OrderRequest;
import com.asmae.shopsphere.model.OrderResponse;
import com.asmae.shopsphere.service.OrderService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequiredArgsConstructor
public class OrderController {
    
    private final OrderService orderService;

    @PostMapping("/orders")
    public ResponseEntity<OrderResponse> createOrder(@Valid @RequestBody OrderRequest order) {
        return ResponseEntity.ok(orderService.createOrder(order));
    }

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }
    
}
