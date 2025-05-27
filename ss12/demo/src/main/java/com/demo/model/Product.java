package com.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product{
    private int id;

    @NotBlank(message = "Name can't not be empty")
    private String name;

    @DecimalMin(value = "0.01", message = "Price must be greater than 0")
    private double price;

    @Min(value = 1, message = "Quantity must be greater than 0")
    private int quantity;
}
