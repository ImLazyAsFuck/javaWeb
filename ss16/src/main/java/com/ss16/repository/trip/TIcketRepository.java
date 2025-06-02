package com.ss16.repository.trip;

import com.ss16.model.ticket.Ticket;

import java.util.List;

public interface TIcketRepository{
    void insertTicket(Ticket ticket);
    List<Ticket> findByUserId(Integer userId);
    Ticket findById(Integer id);
}
