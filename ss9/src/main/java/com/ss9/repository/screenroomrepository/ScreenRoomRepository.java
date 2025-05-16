package com.ss9.repository.screenroomrepository;

import com.ss9.model.ScreenRoom;

import java.util.List;

public interface ScreenRoomRepository{
    List<ScreenRoom> findAll();
    ScreenRoom findById(long id);
}
