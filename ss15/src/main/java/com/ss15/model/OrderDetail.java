package com.ss15.model;

import lombok.*;
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class OrderDetail {
    private int id;
    private int orderId;
    private int productId;
    private int quantity;
    private Double currentPrice;
}