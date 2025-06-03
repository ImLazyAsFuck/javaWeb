package com.ss17.model;


import com.ss17.entity.Product;
import com.ss17.entity.ProductCart;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItemView {
    private ProductCart cart;
    private Product product;

}