package com.ss10.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket{
    private int id;
    private String movieTitle;
    private Date showTime;
    private double totalAmount;
    private List<Seat> seats;
}
