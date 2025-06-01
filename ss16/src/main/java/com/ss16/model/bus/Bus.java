package com.ss16.model.bus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Bus{
    private Long id;
    private String licensePlate;
    private BusType busType;
    private int rowSeat;
    private int colSeat;
    private int totalSeat;
    private String image;
}
