package com.ss9.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Schedule{
    private long id;
    private long movieId;
    private Date showTime;
    private long screenRoomId;
    private int availableSeats;
    private FormatSchedule format;
}
