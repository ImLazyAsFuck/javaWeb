package com.ss16.service.bustrip;

import com.ss16.model.bustrip.BusTrip;

import java.util.List;

public interface BustripService{
    void insertBusTrip(BusTrip trip);
    List<BusTrip> findAll();
    BusTrip findOne(Long id);
    void delete(Long id);
    void update(BusTrip trip);
}
