package com.ss9.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket{
    private long id;
    private long scheduleId;
    private long customerId;
    private List<Seat> seatList;
    private double totalPrice;
    private Date createdAt;
}
