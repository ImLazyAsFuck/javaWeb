package com.ss16.service.bustrip;

import com.ss16.model.bustrip.BusTrip;
import com.ss16.repository.bustrip.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripServiceImpl implements TripService{
    @Autowired
    private TripRepository tripRepository;

    @Override
    public void insertBusTrip(BusTrip trip){
        tripRepository.insertBusTrip(trip);
    }

    @Override
    public List<BusTrip> findAll(){
        return tripRepository.findAll();
    }

    @Override
    public BusTrip findOne(Long id){
        return tripRepository.findOne(id);
    }

    @Override
    public void delete(Long id){
        tripRepository.delete(id);
    }

    @Override
    public void update(BusTrip trip){
        tripRepository.update(trip);
    }
}
