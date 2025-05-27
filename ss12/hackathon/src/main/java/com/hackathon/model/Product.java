package com.hackathon.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product{
    private int id;
    private String name;
    private double price;
    private String image;
    private String description;
    private ProductStatus status;
    private LocalTime createdAt;
}
