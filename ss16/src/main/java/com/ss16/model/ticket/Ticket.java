package com.ss16.model.ticket;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ticket{
    private long id;
    private long userId;
    private long tripBusId;
    private String listSeat;
    private double totalMoney;
    private LocalDate departureDate;
}
