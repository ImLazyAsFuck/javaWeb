package com.ss8.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product{
    private int id;
    private String name;
    private double price;
    private int stock;
}
