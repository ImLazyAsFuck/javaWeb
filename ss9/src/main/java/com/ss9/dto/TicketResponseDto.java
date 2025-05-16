package com.ss9.dto;

import com.ss9.model.Seat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketResponseDto {
    private Long ticketId;
    private Long customerId;
    private Long scheduleId;
    private List<Seat> seats;
    private double totalPrice;
    private Date createdAt;
    private String movieTitle;
    private String screenRoomName;
    private Date showTime;
}
