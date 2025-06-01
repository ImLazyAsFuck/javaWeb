package com.ss16.service.bus;

import com.ss16.model.bus.Bus;

import java.util.List;

public interface BusService{
    List<Bus> findAll();
    Bus findOne(Long id);
    void save(Bus bus);
    void delete(Long id);
    void update(Bus bus);
    boolean exists(String licenseplate);
}
