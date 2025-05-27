package com.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seat {
    private int id;
    @NotBlank(message = "Name of seat can't not be empty")
    private String nameSeat;
    @DecimalMin(value = "0.01", message = "Quantity must be greater than 0")
    private double price;
    private int busId;
    @NotNull(message = "Status can't not be empty")
    private SeatStatus status;
}
