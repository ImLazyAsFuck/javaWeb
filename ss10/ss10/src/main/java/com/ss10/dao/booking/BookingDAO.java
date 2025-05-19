package com.ss10.dao.booking;

import com.ss10.model.Ticket;

import java.util.List;

public interface BookingDAO{
    boolean book(Ticket ticket);
    List<Ticket> findAll();
}
