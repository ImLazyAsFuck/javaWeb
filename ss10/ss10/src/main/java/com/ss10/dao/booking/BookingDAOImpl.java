package com.ss10.dao.booking;

import com.ss10.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import javax.xml.transform.Result;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BookingDAOImpl implements BookingDAO{
    @Autowired
    private DataSource dataSource;

    @Override
    public boolean book(Ticket ticket){
        Connection con = null;
        CallableStatement cs = null;
        try{
            con = dataSource.getConnection();
            cs = con.prepareCall("{call book_ticket(?, ?, ?, ?)}");
            cs.setString(1, ticket.getMovieTitle());
            cs.setDate(2, (Date)ticket.getShowTime());
            cs.setDouble(3, ticket.getTotalAmount());
            cs.setString(4, ticket.getSeats().stream()
                    .map(Object::toString)
                    .collect(Collectors.joining(",")));
            return cs.executeUpdate() > 0;
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                dataSource.getConnection().close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public List<Ticket> findAll(){
        Connection con = null;
        CallableStatement cs = null;
        List<Ticket> tickets = new ArrayList<>();
        try{
            con = dataSource.getConnection();
            cs = con.prepareCall("{call get_all_tickets()}");
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                Ticket ticket = new Ticket();
                ticket.setId(rs.getInt("ticket_id"));
                ticket.setMovieTitle(rs.getString("movie_title"));
                ticket.setShowTime(rs.getDate("show_time"));
//                ti
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                dataSource.getConnection().close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return tickets;
    }
}
