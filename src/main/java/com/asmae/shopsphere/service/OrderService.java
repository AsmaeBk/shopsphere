package com.asmae.shopsphere.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.aspectj.weaver.ast.Or;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import com.asmae.shopsphere.exception.ProductNotFoundException;
import com.asmae.shopsphere.model.Order;
import com.asmae.shopsphere.model.OrderItem;
import com.asmae.shopsphere.model.OrderItemRequest;
import com.asmae.shopsphere.model.OrderItemResponse;
import com.asmae.shopsphere.model.OrderRequest;
import com.asmae.shopsphere.model.OrderResponse;
import com.asmae.shopsphere.model.Product;
import com.asmae.shopsphere.repository.OrderRepository;
import com.asmae.shopsphere.repository.ProductRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final ProductRepository productRepo ;

    @Transactional
    public OrderResponse createOrder(OrderRequest request) {

        Order order = Order.builder()
                .createdAt(LocalDateTime.now())
                .customerName(request.getCustomerName())
                .build();

        List<@Valid OrderItemRequest> itemRequests = request.getItems();
        
        List<OrderItem> items = new ArrayList<>();


        
        for(OrderItemRequest itemReq : itemRequests){            

            Product product = productRepo.findById(itemReq.getProductId())
                    .orElseThrow(() -> new ProductNotFoundException(itemReq.getProductId()));

            OrderItem item = OrderItem.builder()
                    .quantity(itemReq.getQuantity())
                    .product(product)
                    .order(order)
                    .build();


            items.add(item);

        }

        order.setItems(items);

        Order savedOrder = orderRepository.save(order);

        List<OrderItemResponse> itemsRes = savedOrder.getItems()
        .stream()
        .map(item -> OrderItemResponse.builder()
                .id(item.getId())
                .quantity(item.getQuantity())
                .orderId(savedOrder.getId())
                .productId(item.getProduct().getId())
                .build())
        .toList();


        return OrderResponse.builder()
                .id(savedOrder.getId())
                .createdAt(savedOrder.getCreatedAt())
                .customerName(savedOrder.getCustomerName())
                .items(itemsRes)
                .build();
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

}
