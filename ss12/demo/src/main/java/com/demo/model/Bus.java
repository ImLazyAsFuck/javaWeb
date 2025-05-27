package com.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bus {
    private int id;
    private String licensePlate;
    private String busType;
    private int rowSeat;
    private int colSeat;
    private int totalSeat;
    private String image;
}
