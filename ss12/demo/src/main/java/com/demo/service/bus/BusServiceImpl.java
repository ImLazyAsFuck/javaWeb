package com.demo.service.bus;

import com.demo.dao.bus.BusDAO;
import com.demo.model.Bus;
import com.demo.model.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusServiceImpl implements BusService{
    @Autowired
    private BusDAO busDAO;

    @Override
    public boolean addBus(Bus bus){
        return busDAO.addBus(bus);
    }

    @Override
    public boolean updateBus(Bus bus){
        return busDAO.updateBus(bus);
    }

    @Override
    public boolean deleteBus(int id){
        return busDAO.deleteBus(id);
    }

    @Override
    public List<Bus> getAllBuses(){
        return busDAO.getAllBuses();
    }

    @Override
    public List<Seat> getSeatsByBusId(int busId){
        return busDAO.getSeatsByBusId(busId);
    }

    @Override
    public Bus getBusById(int id){
        return busDAO.getBusById(id);
    }
}
