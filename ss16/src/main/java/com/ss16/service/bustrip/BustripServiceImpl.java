package com.ss16.service.bustrip;

import com.ss16.model.bustrip.BusTrip;
import com.ss16.repository.bustrip.BustripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BustripServiceImpl implements BustripService{
    @Autowired
    private BustripRepository bustripRepository;

    @Override
    public void insertBusTrip(BusTrip trip){
        bustripRepository.insertBusTrip(trip);
    }

    @Override
    public List<BusTrip> findAll(){
        return bustripRepository.findAll();
    }

    @Override
    public BusTrip findOne(Long id){
        return bustripRepository.findOne(id);
    }

    @Override
    public void delete(Long id){
        bustripRepository.delete(id);
    }

    @Override
    public void update(BusTrip trip){
        bustripRepository.update(trip);
    }
}
