package com.ss9.repository.ticketrepository;

import com.ss9.model.Seat;
import com.ss9.model.Ticket;

import java.util.List;
import java.util.Map;

public interface TicketRepository {
    List<Seat> getSeatsByScheduleId(Long scheduleId);
    Map<String, Object> getScheduleDetails(Long scheduleId);
    double calculateTicketPrice(List<Long> seatIds);
    Ticket bookTicket(Long customerId, Long scheduleId, List<Long> seatIds);
    Ticket getTicketById(Long ticketId);
    List<Ticket> getTicketsByCustomerId(Long customerId);
}
