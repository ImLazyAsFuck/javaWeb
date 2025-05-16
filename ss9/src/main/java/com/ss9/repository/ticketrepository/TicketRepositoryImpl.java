package com.ss9.repository.ticketrepository;

import com.ss9.model.Seat;
import com.ss9.model.Ticket;
import com.ss9.utils.DBConnect;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TicketRepositoryImpl implements TicketRepository{

    @Override
    public List<Seat> getSeatsByScheduleId(Long scheduleId){
        Connection con = null;
        CallableStatement cs = null;
        List<Seat> seats = List.of();
        try{
            con = DBConnect.getConnection();
            cs = con.prepareCall("{call get_seats_by_schedule_id(?)}");
            cs.setLong(1, scheduleId);
            ResultSet result = cs.executeQuery();
            while(result.next()){
                Seat seat = new Seat();
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }catch(IOException e){
            throw new RuntimeException(e);
        }finally{
            DBConnect.closeConnection(con, cs);
        }
        return seats;
    }

    @Override
    public Map<String, Object> getScheduleDetails(Long scheduleId) {
        Connection con = null;
        CallableStatement cs = null;
        ResultSet result = null;
        Map<String, Object> scheduleDetails = new HashMap<>();

        try {
            con = DBConnect.getConnection();
            cs = con.prepareCall("{call get_schedule_details(?)}");
            cs.setLong(1, scheduleId);
            result = cs.executeQuery();

            if (result.next()) {
                scheduleDetails.put("screenRoomName", result.getString("screen_room_name"));
                scheduleDetails.put("showTime", result.getTimestamp("show_time"));
                scheduleDetails.put("movieTitle", result.getString("movie_title"));
                scheduleDetails.put("availableSeats", result.getInt("available_seats"));
                scheduleDetails.put("format", result.getString("format"));
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        } finally {
            DBConnect.closeConnection(con, cs);
        }

        return scheduleDetails;
    }


    @Override
    public double calculateTicketPrice(List<Long> seatIds){
        Connection con = null;
        CallableStatement cs = null;
        ResultSet result = null;
        double totalPrice = 0.0;
        try{
            con = DBConnect.getConnection();
            cs = con.prepareCall("{call calculate_ticket_price(?)}");
            cs.setArray(1, con.createArrayOf("bigint", seatIds.toArray()));
            result = cs.executeQuery();
            if(result.next()){
                totalPrice = result.getDouble("total_price");
            }
        }catch(SQLException | IOException e){
            throw new RuntimeException(e);
        }finally{
            DBConnect.closeConnection(con, cs);
        }
        return totalPrice;
    }

    @Override
    public Ticket bookTicket(Long customerId, Long scheduleId, List<Long> seatIds){
        Connection con = null;
        CallableStatement cs = null;
        ResultSet result = null;
        Ticket ticket = null;
        try{
            con = DBConnect.getConnection();
            cs = con.prepareCall("{call book_ticket(?, ?, ?)}");
            cs.setLong(1, customerId);
            cs.setLong(2, scheduleId);
            cs.setArray(3, con.createArrayOf("bigint", seatIds.toArray()));
        }catch(SQLException | IOException e){
            throw new RuntimeException(e);
        }finally{
            DBConnect.closeConnection(con, cs);
        }
        return ticket;
    }

    @Override
    public Ticket getTicketById(Long ticketId){
        Connection con = null;
        CallableStatement cs = null;
        ResultSet result = null;
        Ticket ticket = null;
        try{
            con = DBConnect.getConnection();
            cs = con.prepareCall("{call get_ticket_by_id(?)}");
            cs.setLong(1, ticketId);
            result = cs.executeQuery();
            if(result.next()){}
        }catch(SQLException e){
            throw new RuntimeException(e);
        }catch(IOException e){
            throw new RuntimeException(e);
        }finally{
            DBConnect.closeConnection(con, cs);
        }
        return ticket;
    }

    @Override
    public List<Ticket> getTicketsByCustomerId(Long customerId){
        Connection con = null;
        CallableStatement cs = null;
        ResultSet result = null;
        List<Ticket> tickets = List.of();
        try{
            con = DBConnect.getConnection();
            cs = con.prepareCall("{call get_tickets_by_customer_id(?)}");
            cs.setLong(1, customerId);
            result = cs.executeQuery();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }catch(IOException e){
            throw new RuntimeException(e);
        }finally{
            DBConnect.closeConnection(con, cs);
        }
        return tickets;
    }
}
