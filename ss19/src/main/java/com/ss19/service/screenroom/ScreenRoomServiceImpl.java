package com.ss19.service.screenroom;

import com.ss19.entity.ScreenRoom;
import com.ss19.entity.Seat;
import com.ss19.repo.screenroom.ScreenRoomRepo;
import com.ss19.repo.seat.SeatRepo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScreenRoomServiceImpl implements ScreenRoomService{
    @Autowired
    SessionFactory sessionFactory;
    @Autowired
    private ScreenRoomRepo screenRoomRepo;
    @Autowired
    private SeatRepo seatRepo;

    @Override
    public List<ScreenRoom> findAllActive(){
        return screenRoomRepo.findAllActive();
    }

    @Override
    public ScreenRoom findById(Long id){
        return screenRoomRepo.findById(id);
    }

    @Override
    public void save(ScreenRoom screenRoom) {
        screenRoomRepo.save(screenRoom);
    }

    @Override
    public void delete(ScreenRoom screenRoom){
        screenRoomRepo.delete(screenRoom);
    }

    public void updateScreenRoom(ScreenRoom updatedRoom) {
       screenRoomRepo.save(updatedRoom);
    }


    private int extractNumber(String seatNumber) {
        return Integer.parseInt(seatNumber.replaceAll("\\D+", ""));
    }

}
