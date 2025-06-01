package com.ss16.service.bus;

import com.ss16.model.bus.Bus;
import com.ss16.repository.bus.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusServiceImpl implements BusService{
    @Autowired
    private BusRepository busRepository;

    @Override
    public List<Bus> findAll(){
        return busRepository.findAll();
    }

    @Override
    public Bus findOne(Long id){
        return busRepository.findOne(id);
    }

    @Override
    public void save(Bus bus){
        busRepository.save(bus);
    }

    @Override
    public void delete(Long id){
        busRepository.delete(id);
    }

    @Override
    public void update(Bus bus){
        busRepository.update(bus);
    }

    @Override
    public boolean exists(String licenseplate){
        return busRepository.exists(licenseplate);
    }
}
