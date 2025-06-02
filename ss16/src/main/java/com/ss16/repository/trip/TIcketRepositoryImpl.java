package com.ss16.repository.trip;

import com.ss16.model.ticket.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public class TIcketRepositoryImpl implements TIcketRepository{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void insertTicket(Ticket ticket){
        String sql = "{call insert_ticket(?,?,?,?,?)}";
        jdbcTemplate.update(sql, ticket.getTripBusId(), ticket.getUserId(), ticket.getListSeat(), ticket.getTotalMoney(),  Date.valueOf(ticket.getDepartureDate()));
    }

    @Override
    public List<Ticket> findByUserId(Integer userId){
        String sql = "{call get_tickets_by_user(?)}";
        return jdbcTemplate.query(sql, new Object[]{userId}, (rs, rowNum) ->
                new Ticket(
                        rs.getLong("id"),
                        rs.getLong("trip_bus_id"),
                        rs.getLong("user_id"),
                        rs.getString("list_seat"),
                        rs.getDouble("total_money"),
                        rs.getDate("departure_date").toLocalDate()
                )
        );
    }

    @Override
    public Ticket findById(Integer id){
        String sql = "{call get_ticket_by_id(?)}";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) ->
                new Ticket(
                        rs.getLong("id"),
                        rs.getLong("trip_bus_id"),
                        rs.getLong("user_id"),
                        rs.getString("list_seat"),
                        rs.getDouble("total_money"),
                        rs.getDate("departure_date").toLocalDate()
                )
        );
    }

}
