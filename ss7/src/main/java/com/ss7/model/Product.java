package com.ss7.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private int id;
    private String name;
    private double price;
    private int stock;
    private String description;
    private String image;
    private int categoryId;
    private String color;
    private String size;
    private String brand;
    
    // Constructor matching the previous one for compatibility
    public Product(int id, String name, double price, int stock, String description, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.description = description;
        this.image = image;
    }
    
    // Constructor with categoryId
    public Product(int id, String name, double price, int stock, String description, String image, int categoryId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.description = description;
        this.image = image;
        this.categoryId = categoryId;
    }
    
    // Constructor with all fields except id
    public Product(String name, double price, int stock, String description, String image, 
                   int categoryId, String color, String size, String brand) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.description = description;
        this.image = image;
        this.categoryId = categoryId;
        this.color = color;
        this.size = size;
        this.brand = brand;
    }
}