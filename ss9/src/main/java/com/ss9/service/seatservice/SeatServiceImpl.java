package com.ss9.service.seatservice;

import com.ss9.model.Seat;
import com.ss9.repository.seatrepository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatServiceImpl implements SeatService{
    @Autowired
    private SeatRepository seatRepository;

    @Override
    public List<Seat> findAll(){
        return seatRepository.findAll();
    }

    @Override
    public Seat findById(Long id){
        return seatRepository.findById(id);
    }

    @Override
    public List<Seat> findByRoomId(Long roomId){
        return seatRepository.findByRoomId(roomId);
    }
}
