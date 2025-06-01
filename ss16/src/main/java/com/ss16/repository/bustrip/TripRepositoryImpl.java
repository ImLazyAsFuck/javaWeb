package com.ss16.repository.bustrip;

import com.ss16.model.bustrip.BusTrip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TripRepositoryImpl implements TripRepository{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void insertBusTrip(BusTrip trip){
        String sql = "CALL insert_bustrip(?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                trip.getDeparture(),
                trip.getDestination(),
                trip.getDepartureTime(),
                trip.getArrivalTime(),
                trip.getBusId(),
                trip.getSeatsAvailable(),
                trip.getImage());
    }

    @Override
    public List<BusTrip> findAll() {
        String sql = "CALL get_all_bustrip()";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new BusTrip(
                        rs.getLong("id"),
                        rs.getString("departure_point"),
                        rs.getString("destination"),
                        rs.getTimestamp("departure_time").toLocalDateTime(),
                        rs.getTimestamp("arrival_time").toLocalDateTime(),
                        rs.getInt("bus_id"),
                        rs.getInt("seats_available"),
                        rs.getString("image")
                )
        );
    }


    @Override
    public BusTrip findOne(Long id){
        String sql = "CALL get_bustrip_by_id(?)";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) ->
                new BusTrip(
                        rs.getLong("id"),
                        rs.getString("departure_point"),
                        rs.getString("destination"),
                        rs.getTimestamp("departure_time").toLocalDateTime(),
                        rs.getTimestamp("arrival_time").toLocalDateTime(),
                        rs.getInt("bus_id"),
                        rs.getInt("seats_available"),
                        rs.getString("image")
                )
        );
    }

    @Override
    public void delete(Long id){
        String sql = "CALL delete_bustrip(?)";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void update(BusTrip trip){
        String sql = "CALL update_bustrip(?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                trip.getId(),
                trip.getDeparture(),
                trip.getDestination(),
                trip.getDepartureTime(),
                trip.getArrivalTime(),
                trip.getBusId(),
                trip.getSeatsAvailable(),
                trip.getImage()
        );
    }
}
