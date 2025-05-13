package com.example.ss6.model;

import lombok.Data;

@Data
public class Book{
    private int id;
    private String title;
    private String author;
    private String category;
    private int stock;
}
