package com.demo.service.bus;

import com.demo.model.Bus;
import com.demo.model.Seat;

import java.util.List;

public interface BusService{
    boolean addBus(Bus bus);
    boolean updateBus(Bus bus);
    boolean deleteBus(int id);
    List<Bus> getAllBuses();
    List<Seat> getSeatsByBusId(int busId);
    Bus getBusById(int id);
}
