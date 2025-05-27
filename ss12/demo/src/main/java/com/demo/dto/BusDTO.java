package com.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusDTO {
    private int id;

    @NotBlank(message = "License plate cannot be empty")
    @Size(max = 150, message = "License plate must be at most 150 characters")
    private String licensePlate;

    @NotBlank(message = "Bus type cannot be empty")
    @Pattern(regexp = "VIP|LUXURY|NORMAL", message = "Bus type must be VIP, LUXURY, or NORMAL")
    private String busType;

    @Min(value = 1, message = "Number of rows must be at least 1")
    private int rowSeat;

    @Min(value = 1, message = "Number of columns must be at least 1")
    private int colSeat;

    private int totalSeat;

    @NotEmptyMultipartFile(message = "Image cannot be empty")
    private MultipartFile image;

    private String currentImage;
}