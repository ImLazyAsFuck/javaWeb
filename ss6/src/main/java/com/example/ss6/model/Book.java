package com.example.ss6.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book{
    private int id;
    private String title;
    private String author;
    private String category;
    private int stock;
}
