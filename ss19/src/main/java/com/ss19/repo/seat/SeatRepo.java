package com.ss19.repo.seat;

import com.ss19.entity.Seat;

import java.util.List;

public interface SeatRepo{
    void save(Seat seat);
    void saveAll(List<Seat> seats);
    public List<Seat> findByScreenRoomId(Long screenRoomId);
    void delete(Seat seat);
}
