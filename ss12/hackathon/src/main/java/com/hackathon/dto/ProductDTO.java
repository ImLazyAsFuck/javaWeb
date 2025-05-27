package com.hackathon.dto;

import com.hackathon.model.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO{
    private int id;

    @UniqueProductName()
    @NotBlank(message = "Product name cannot be empty")
    private String name;

    @DecimalMin(value = "0.01", message = "Product price must be greater than 0")
    private double price;

    @NotEmptyMultipartFile(message = "Product image cannot be empty")
    private MultipartFile image;

    private String description;

    @Pattern(regexp = "^(AVAILABLE|NOT_AVAILABLE)$", message = "Product status must be either AVAILABLE or NOT_AVAILABLE")
    private String status;

    private String img;
}
