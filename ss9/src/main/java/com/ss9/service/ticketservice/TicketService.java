package com.ss9.service.ticketservice;

import com.ss9.model.Seat;
import com.ss9.model.Ticket;

import java.util.List;
import java.util.Map;

public interface TicketService {
    List<Seat> getSeatsByScheduleId(Long scheduleId);
    Map<String, Object> getScheduleDetails(Long scheduleId);
    double calculateTicketPrice(List<Long> seatIds);
    Ticket bookTicket(Long customerId, Long scheduleId, List<Long> seatIds);
    Ticket getTicketById(Long ticketId);
    List<Ticket> getTicketsByCustomerId(Long customerId);
}
