package com.ss17.dto;

import com.ss17.validator.uniqueproductname.IsProductNameExist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto{
    private int id;

    @NotBlank(message = "Product name cannot be empty")
    @IsProductNameExist
    private String productName;

    private String description;

    @DecimalMin(value = "0.01", message = "Price must be greater than or equal to 0")
    private double price;

    @Min(value = 1, message = "Stock must be greater than or equal to 1")
    private int stock;

    private MultipartFile file;

    private String image;
}
