package com.ss9.service.screenroomservice;

import com.ss9.model.ScreenRoom;
import com.ss9.repository.screenroomrepository.ScreenRoomRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScreenRoomServiceImpl implements ScreenRoomService{
    
    @Autowired
    private ScreenRoomRepositoryImpl screenRoomRepositoryImpl;
    
    public List<ScreenRoom> findAll() {
        return screenRoomRepositoryImpl.findAll();
    }
    
    public ScreenRoom findById(long id) {
        return screenRoomRepositoryImpl.findById(id);
    }
}
