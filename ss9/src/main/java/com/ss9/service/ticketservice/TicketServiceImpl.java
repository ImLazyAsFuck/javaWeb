package com.ss9.service.ticketservice;

import com.ss9.model.Seat;
import com.ss9.model.Ticket;
import com.ss9.repository.ticketrepository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketRepository ticketRepository;


    @Override
    public List<Seat> getSeatsByScheduleId(Long scheduleId) {
        return ticketRepository.getSeatsByScheduleId(scheduleId);
    }

    @Override
    public Map<String, Object> getScheduleDetails(Long scheduleId) {
        return ticketRepository.getScheduleDetails(scheduleId);
    }

    @Override
    public double calculateTicketPrice(List<Long> seatIds) {
        return ticketRepository.calculateTicketPrice(seatIds);
    }

    @Override
    public Ticket bookTicket(Long customerId, Long scheduleId, List<Long> seatIds) {
        if (customerId == null || scheduleId == null || seatIds == null || seatIds.isEmpty()) {
            throw new IllegalArgumentException("Invalid booking parameters");
        }

        List<Seat> availableSeats = getSeatsByScheduleId(scheduleId);
        List<Long> availableSeatIds = new ArrayList<>();
        for (Seat seat : availableSeats) {
            if (!seat.isStatus()) {
                availableSeatIds.add(seat.getId());
            }
        }

        for (Long seatId : seatIds) {
            if (!availableSeatIds.contains(seatId)) {
                throw new IllegalStateException("Seat " + seatId + " is not available");
            }
        }
        
        return ticketRepository.bookTicket(customerId, scheduleId, seatIds);
    }

    @Override
    public Ticket getTicketById(Long ticketId) {
        return ticketRepository.getTicketById(ticketId);
    }

    @Override
    public List<Ticket> getTicketsByCustomerId(Long customerId) {
        return ticketRepository.getTicketsByCustomerId(customerId);
    }
}
