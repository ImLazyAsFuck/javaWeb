package com.ss7.model;

import lombok.Data;

@Data
public class Product{
    private int id;
    private String name;
    private double price;
    private int stock;
    private String description;
    private String image;
}