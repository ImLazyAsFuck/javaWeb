package com.ss9.service.seatservice;

import com.ss9.model.Seat;

import java.util.List;

public interface SeatService {
    List<Seat> findAll();
    Seat findById(Long id);
    List<Seat> findByRoomId(Long roomId);
}
