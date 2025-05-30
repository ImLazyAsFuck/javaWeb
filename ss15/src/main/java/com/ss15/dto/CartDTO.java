package com.ss15.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO{
    private int id;
    private String productName;
    private double productPrice;
    private int quantity;
    private double totalPrice;
}
