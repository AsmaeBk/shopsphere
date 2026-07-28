package com.asmae.shopsphere.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    
    private Long id;
    private LocalDateTime createdAt;
    private String customerName;
    private List<OrderItemResponse> items = new ArrayList<>();
}
