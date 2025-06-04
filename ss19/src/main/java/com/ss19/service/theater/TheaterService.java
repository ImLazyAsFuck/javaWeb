package com.ss19.service.theater;

import com.ss19.entity.Theater;

import java.util.List;

public interface TheaterService{
    List<Theater> findAll();
    Theater findById(Long theaterId);
    void save(Theater theater);
    void delete(Long id);
}
