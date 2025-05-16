package com.ss9.repository.seatrepository;

import com.ss9.model.Seat;

import java.util.List;

public interface SeatRepository {
    List<Seat> findAll();
    Seat findById(Long id);
    List<Seat> findByRoomId(Long roomId);
}
