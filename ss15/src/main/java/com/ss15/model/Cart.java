package com.ss15.model;

import lombok.*;
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Cart {
    private int id;
    private int userId;
    private int productId;
    private int quantity;

}
