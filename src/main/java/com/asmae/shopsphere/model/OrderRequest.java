package com.asmae.shopsphere.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class OrderRequest {

    private String customerName;


    private List<OrderItemRequest> items = new ArrayList<>();
}
