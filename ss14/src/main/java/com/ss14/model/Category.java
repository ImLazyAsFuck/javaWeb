package com.ss14.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category{
    private int id;
    private String categoryName;
    private String description;

    public Category(String categoryName, String description) {
        this.categoryName = categoryName;
        this.description = description;
    }
}
