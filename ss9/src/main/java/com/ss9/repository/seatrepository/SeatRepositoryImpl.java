package com.ss9.repository.seatrepository;

import com.ss9.model.Seat;
import com.ss9.utils.DBConnect;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SeatRepositoryImpl implements SeatRepository{
    @Override
    public List<Seat> findAll(){
        Connection con = null;
        CallableStatement cs = null;
        List<Seat> seats = new ArrayList<>();
        try{
            con = DBConnect.getConnection();
            cs = con.prepareCall("{call get_all_seat()}");
            ResultSet result = cs.executeQuery();
            while(result.next()){
                Seat seat = new Seat();
                seat.setId(result.getLong("s_id"));
                seat.setPrice(result.getDouble("s_price"));
                seat.setScreenRoomId(result.getLong("sr_id"));
                seat.setStatus(result.getBoolean("s_status"));
                seats.add(seat);
            }
        }catch(Exception e){
            e.fillInStackTrace();
        }finally{
            DBConnect.closeConnection(con, cs);
        }
        return seats;
    }

    @Override
    public Seat findById(Long id){
        Connection con = null;
        CallableStatement cs = null;
        Seat seat = null;
        try{
            con = DBConnect.getConnection();
            cs = con.prepareCall("{call get_seat_by_id(?)}");
            cs.setLong(1, id);
            ResultSet result = cs.executeQuery();
            while(result.next()){
                seat = new Seat();
                seat.setId(result.getLong("s_id"));
                seat.setPrice(result.getDouble("s_price"));
                seat.setScreenRoomId(result.getLong("sr_id"));
                seat.setStatus(result.getBoolean("s_status"));

            }
        }catch(Exception e){
            e.fillInStackTrace();
        }finally{
            DBConnect.closeConnection(con, cs);
        }
        return seat;
    }

    @Override
    public List<Seat> findByRoomId(Long roomId){
        Connection con = null;
        CallableStatement cs = null;
        List<Seat> seats = new ArrayList<>();
        try{
            con = DBConnect.getConnection();
            cs = con.prepareCall("{call get_seat_by_room_id(?)}");
            cs.setLong(1, roomId);
            ResultSet result = cs.executeQuery();
            while(result.next()){
                Seat seat = new Seat();
                seat.setId(result.getLong("s_id"));
                seat.setPrice(result.getDouble("s_price"));
                seat.setScreenRoomId(result.getLong("sr_id"));
                seat.setStatus(result.getBoolean("s_status"));
                seats.add(seat);
            }
        }catch(Exception e){
            e.fillInStackTrace();
        }finally{
            DBConnect.closeConnection(con, cs);
        }
        return seats;
    }
}
