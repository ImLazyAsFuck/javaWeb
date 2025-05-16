package com.ss9.repository.schedulerepository;

import com.ss9.model.Schedule;
import java.util.List;

public interface ScheduleRepository {
    List<Schedule> findAll();
    Schedule findById(long id);
    List<Schedule> findAllByMovieId(Long movieId);
}
