package com.ss9.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seat{
    private long id;
    private long screenRoomId;
    private double price;
    private boolean status;
}
