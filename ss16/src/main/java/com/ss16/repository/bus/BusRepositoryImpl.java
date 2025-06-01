package com.ss16.repository.bus;

import com.ss16.model.bus.Bus;
import com.ss16.model.bus.BusType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BusRepositoryImpl implements BusRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Bus> findAll() {
        String sql = "CALL get_all_buses()";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new Bus(
                        rs.getLong("id"),
                        rs.getString("licenseplate"),
                        BusType.valueOf(rs.getString("bus_type")),
                        rs.getInt("row_seat"),
                        rs.getInt("col_seat"),
                        rs.getInt("total_seat"),
                        rs.getString("image")
                )
        );
    }

    @Override
    public Bus findOne(Long id) {
        String sql = "CALL get_bus_by_id(?)";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) ->
                new Bus(
                        rs.getLong("id"),
                        rs.getString("licenseplate"),
                        BusType.valueOf(rs.getString("bus_type")),
                        rs.getInt("row_seat"),
                        rs.getInt("col_seat"),
                        rs.getInt("total_seat"),
                        rs.getString("image")
                )
        );
    }

    @Override
    public void save(Bus bus) {
        String sql = "CALL insert_bus(?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                bus.getLicensePlate(),
                bus.getBusType().name(),
                bus.getRowSeat(),
                bus.getColSeat(),
                bus.getImage()
        );
    }

    @Override
    public void delete(Long id) {
        String sql = "CALL delete_bus(?)";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void update(Bus bus) {
        String sql = "CALL update_bus(?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                bus.getId(),
                bus.getLicensePlate(),
                bus.getBusType().name(),
                bus.getRowSeat(),
                bus.getColSeat(),
                bus.getImage()
        );
    }

    @Override
    public boolean exists(String licenseplate){
        String sql = "CALL unique_licenseplate(?)";
        return jdbcTemplate.queryForObject(sql, Boolean.class, licenseplate);
    }
}
