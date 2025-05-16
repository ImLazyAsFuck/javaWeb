package com.ss9.service.scheduleservice;

import com.ss9.model.Schedule;
import com.ss9.repository.schedulerepository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService{
    
    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public List<Schedule> findAll() {
        return scheduleRepository.findAll();
    }

    @Override
    public Schedule findById(long id){
        return scheduleRepository.findById(id);
    }


    @Override
    public List<Schedule> findAllByMovieId(Long movieId){
        return scheduleRepository.findAllByMovieId(movieId);
    }
}
