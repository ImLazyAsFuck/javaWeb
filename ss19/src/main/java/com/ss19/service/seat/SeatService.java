package com.ss19.service.seat;

import com.ss19.entity.Seat;

import java.util.List;

public interface SeatService{
    void save(Seat seat);
    void saveAll(List<Seat> seats);
    List<Seat> findByScreenRoomId(Long screenRoomId);
    void delete(Seat seat);
}
