package com.ss19.repo.screenroom;

import com.ss19.entity.ScreenRoom;
import com.ss19.entity.Seat;

import java.util.List;
import java.util.Optional;

public interface ScreenRoomRepo{
   List<ScreenRoom> findAllActive();
   ScreenRoom findById(Long id);
   void save(ScreenRoom screenRoom);
   void delete(ScreenRoom screenRoom);
}
