package com.ss9.repository.schedulerepository;

import com.ss9.model.FormatSchedule;
import com.ss9.model.Schedule;
import com.ss9.utils.DBConnect;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ScheduleRepositoryImpl implements ScheduleRepository{

    public List<Schedule> findAll() {
        Connection con = null;
        CallableStatement cs = null;
        List<Schedule> schedules = new ArrayList<>();
        try{
            con = DBConnect.getConnection();
            cs = con.prepareCall("{call get_all_schedule()}");
            ResultSet result = cs.executeQuery();
            System.out.println(result.next());
            while(result.next()){
                Schedule schedule = new Schedule();
                schedule.setId(result.getLong("sc_id"));
                schedule.setMovieId(result.getLong("m_id"));
                schedule.setShowTime(result.getDate("sc_show_time"));
                schedule.setScreenRoomId(result.getLong("sr_id"));
                schedule.setAvailableSeats(result.getInt("sc_available_seats"));
                schedule.setFormat(FormatSchedule.valueOf(result.getString("sc_format")));
                System.out.println(schedule);
                schedules.add(schedule);
            }
        }catch(Exception e){
            e.fillInStackTrace();
            e.printStackTrace();
        }finally{
            DBConnect.closeConnection(con, cs);
        }
        return schedules;
    }

    @Override
    public Schedule findById(long id){
        Connection con = null;
        CallableStatement cs = null;
        Schedule schedule = null;
        try{
            con = DBConnect.getConnection();
            cs = con.prepareCall("{call get_schedule_by_id(?)}");
            cs.setLong(1, id);
            ResultSet result = cs.executeQuery();
            while(result.next()){
                schedule = new Schedule();
                schedule.setId(result.getLong("sc_id"));
                schedule.setMovieId(result.getLong("m_id"));
                schedule.setShowTime(result.getDate("sc_show_time"));
                schedule.setScreenRoomId(result.getLong("sr_id"));
                schedule.setAvailableSeats(result.getInt("sc_available_seats"));
                schedule.setFormat(FormatSchedule.valueOf(result.getString("sc_format")));
            }
        }catch(Exception e){
            e.fillInStackTrace();
        }finally{
            DBConnect.closeConnection(con, cs);
        }
        return schedule;
    }


    @Override
    public List<Schedule> findAllByMovieId(Long movieId){
        Connection con = null;
        CallableStatement cs = null;
        List<Schedule> schedules = new ArrayList<>();
        try{
            con = DBConnect.getConnection();
            cs = con.prepareCall("{call get_all_schedule_by_movie_id(?)}");
            cs.setLong(1, movieId);
            ResultSet result = cs.executeQuery();
            while(result.next()){
                Schedule schedule = new Schedule();
                schedule.setId(result.getLong("sc_id"));
                schedule.setMovieId(result.getLong("m_id"));
                schedule.setShowTime(result.getDate("sc_show_time"));
                schedule.setScreenRoomId(result.getLong("sr_id"));
                schedule.setAvailableSeats(result.getInt("sc_available_seats"));
                schedule.setFormat(FormatSchedule.valueOf(result.getString("sc_format")));
                schedules.add(schedule);
            }
        }catch(Exception e){
            e.fillInStackTrace();
            e.printStackTrace();
        }finally{
            DBConnect.closeConnection(con, cs);
        }
        return schedules;
    }

}
