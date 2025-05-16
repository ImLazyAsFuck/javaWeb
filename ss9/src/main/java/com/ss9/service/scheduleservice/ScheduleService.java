package com.ss9.service.scheduleservice;

import com.ss9.model.Schedule;
import java.util.List;

public interface ScheduleService {
    List<Schedule> findAll();
    List<Schedule> findAllScheduleByMovie(String movieTitle);
    List<Schedule> findAllByMovieId(Long movieId);
}
