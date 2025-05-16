package com.ss9.repository.screenroomrepository;


import com.ss9.model.ScreenRoom;
import com.ss9.utils.DBConnect;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ScreenRoomRepositoryImpl implements ScreenRoomRepository{
    @Override
    public List<ScreenRoom> findAll(){
        Connection con = null;
        CallableStatement cs = null;
        List<ScreenRoom> screenRooms = new ArrayList<>();
        try{
            con = DBConnect.getConnection();
            cs = con.prepareCall("{call get_all_screen_room()}");
            ResultSet result = cs.executeQuery();
            System.out.println(result);
            while(result.next()){
                ScreenRoom screenRoom = new ScreenRoom();
                screenRoom.setId(result.getLong("sr_id"));
                screenRoom.setScreenRoomName(result.getString("sr_name"));
                screenRoom.setTotalSeats(result.getInt("sr_total_seats"));
                screenRooms.add(screenRoom);
            }
        }catch(Exception e){
            e.fillInStackTrace();
        }finally{
            DBConnect.closeConnection(con, cs);
        }
        return screenRooms;
    }

    @Override
    public ScreenRoom findById(long id){
        Connection con = null;
        CallableStatement cs = null;
        ScreenRoom screenRoom = null;
        try{
            con = DBConnect.getConnection();
            cs = con.prepareCall("{call get_screen_room_by_id(?)}");
            cs.setLong(1, id);
            ResultSet result = cs.executeQuery();
            while(result.next()){
                screenRoom = new ScreenRoom();
            }
        }catch(Exception e){
            e.fillInStackTrace();
        }finally{
            DBConnect.closeConnection(con, cs);
        }
        return screenRoom;
    }
}
