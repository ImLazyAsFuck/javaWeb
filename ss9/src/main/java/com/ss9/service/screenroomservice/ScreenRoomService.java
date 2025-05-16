package com.ss9.service.screenroomservice;

import com.ss9.model.ScreenRoom;

import java.util.List;

public interface ScreenRoomService{
    List<ScreenRoom> findAll();
    ScreenRoom findById(long id);
}
