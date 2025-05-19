package com.ss10.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seat{
    private int id;
    private String seatNumber;
    private boolean status;
    private double price;
}
