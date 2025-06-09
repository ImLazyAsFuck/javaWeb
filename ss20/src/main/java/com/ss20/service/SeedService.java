package com.ss20.service;

import com.ss20.entity.Seed;

import java.util.List;

public interface SeedService{
    boolean existsBySeedNameIgnoreCase(String seedName);
    List<Seed> findAll();
    void save(Seed seed);
    void update(Seed seed);
    void delete(Seed seed);
    List<Seed> findBySeedName(String seedName);
}
