package com.ss9.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDetailsDto {
    private String screenRoomName;
    private Date showTime;
    private String movieTitle;
    private int availableSeats;
    private String format;
}
