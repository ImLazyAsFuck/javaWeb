package com.ss14.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO{
    private int id;

    @NotBlank(message = "Product name can't be empty")
    private String name;

    @Min(value = 0, message = "Price must be greater than or equal to zero")
    private double price;
}
