package com.example.ss6.model;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCart {
    private int id;
    private String sessionId;
    private int productId;
    private int quantity;

    private Product product;
}
