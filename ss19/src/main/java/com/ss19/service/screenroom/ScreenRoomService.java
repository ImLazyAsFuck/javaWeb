package com.ss19.service.screenroom;

import com.ss19.entity.ScreenRoom;

import java.util.List;

public interface ScreenRoomService{
    List<ScreenRoom> findAllActive();
    ScreenRoom findById(Long id);
    void save(ScreenRoom screenRoom);
    void delete(ScreenRoom screenRoom);
    void updateScreenRoom(ScreenRoom updatedRoom);
}
