package com.ss20.service;

import com.ss20.entity.Seed;
import com.ss20.repository.SeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeedServiceImpl implements SeedService{
    @Autowired
    private SeedRepository seedRepository;

    @Override
    public boolean existsBySeedNameIgnoreCase(String seedName){
        return seedRepository.existsBySeedNameIgnoreCase(seedName);
    }

    @Override
    public List<Seed> findAll(){
        return seedRepository.findAll();
    }

    @Override
    public void save(Seed seed){
        seedRepository.save(seed);
    }

    @Override
    public void update(Seed seed){
        seedRepository.save(seed);
    }

    @Override
    public void delete(Seed seed){
        seedRepository.delete(seed);
    }

    @Override
    public List<Seed> findBySeedName(String seedName){
        return seedRepository.findBySeedName(seedName);
    }
}
