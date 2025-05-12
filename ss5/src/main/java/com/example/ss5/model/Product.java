package com.example.ss5.model;

import lombok.Data;

@Data
public class Product{
    private static int idSequence = 0;
    private int id;
    private String name;
    private double price;
    private String description;

    public Product(){
        this.id = ++idSequence;
    }

    public Product(String name, double price, String description){
        this.id = ++idSequence;
        this.name = name;
        this.price = price;
        this.description = description;
    }
}
