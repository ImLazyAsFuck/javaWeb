package com.ss14.dto;

import com.ss14.model.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO{
    private int cartId;
    private Product product;
    private int quantity;
}
