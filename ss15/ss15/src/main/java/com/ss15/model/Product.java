package com.ss15.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product{
    private Long id;
    private String code;
    private String name;
    private double price;
    private String description;
}
