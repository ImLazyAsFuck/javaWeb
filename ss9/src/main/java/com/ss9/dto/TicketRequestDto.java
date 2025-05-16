package com.ss9.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketRequestDto {
    private Long customerId;
    private Long scheduleId;
    private List<Long> seatIds;
}
