package com.demo.dao.bus;

import com.demo.model.Bus;
import com.demo.model.Seat;
import com.demo.model.SeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BusDAOImp implements BusDAO {
    @Autowired
    private DataSource dataSource;

    @Override
    public boolean addBus(Bus bus) {
        try (Connection con = dataSource.getConnection();
             CallableStatement cs = con.prepareCall("{call add_bus(?, ?, ?, ?, ?)}")) {
            cs.setString(1, bus.getLicensePlate());
            cs.setString(2, bus.getBusType());
            cs.setInt(3, bus.getRowSeat());
            cs.setInt(4, bus.getColSeat());
            cs.setString(5, bus.getImage());
            int rowsAffected = cs.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateBus(Bus bus) {
        try (Connection con = dataSource.getConnection();
             CallableStatement cs = con.prepareCall("{call update_bus(?, ?, ?, ?, ?, ?)}")) {
            cs.setInt(1, bus.getId());
            cs.setString(2, bus.getLicensePlate());
            cs.setString(3, bus.getBusType());
            cs.setInt(4, bus.getRowSeat());
            cs.setInt(5, bus.getColSeat());
            cs.setString(6, bus.getImage());
            int rowsAffected = cs.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteBus(int id) {
        try (Connection con = dataSource.getConnection();
             CallableStatement cs = con.prepareCall("{call delete_bus(?)}")) {
            cs.setInt(1, id);
            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Bus> getAllBuses() {
        List<Bus> buses = new ArrayList<>();
        try (Connection con = dataSource.getConnection();
             CallableStatement cs = con.prepareCall("{call list_buses()}");
             ResultSet rs = cs.executeQuery()) {
            while (rs.next()) {
                Bus bus = new Bus();
                bus.setId(rs.getInt("id"));
                bus.setLicensePlate(rs.getString("license_plate"));
                bus.setBusType(rs.getString("bus_type"));
                bus.setRowSeat(rs.getInt("row_seat"));
                bus.setColSeat(rs.getInt("col_seat"));
                bus.setTotalSeat(rs.getInt("total_seat"));
                bus.setImage(rs.getString("image"));
                buses.add(bus);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return buses;
    }

    @Override
    public List<Seat> getSeatsByBusId(int busId) {
        List<Seat> seats = new ArrayList<>();
        try (Connection con = dataSource.getConnection();
             CallableStatement cs = con.prepareCall("{call show_bus_seats(?)}")) {
            cs.setInt(1, busId);
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    Seat seat = new Seat();
                    seat.setNameSeat(rs.getString("name_seat"));
                    seat.setPrice(rs.getDouble("price"));
                    seat.setStatus(SeatStatus.valueOf(rs.getString("status")));
                    seat.setBusId(busId);
                    seats.add(seat);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seats;
    }

    @Override
    public Bus getBusById(int id) {
        try (Connection con = dataSource.getConnection();
             CallableStatement cs = con.prepareCall("{call list_buses()}");
             ResultSet rs = cs.executeQuery()) {
            while (rs.next()) {
                if (rs.getInt("id") == id) {
                    Bus bus = new Bus();
                    bus.setId(rs.getInt("id"));
                    bus.setLicensePlate(rs.getString("license_plate"));
                    bus.setBusType(rs.getString("bus_type"));
                    bus.setRowSeat(rs.getInt("row_seat"));
                    bus.setColSeat(rs.getInt("col_seat"));
                    bus.setTotalSeat(rs.getInt("total_seat"));
                    bus.setImage(rs.getString("image"));
                    return bus;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}