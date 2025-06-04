package com.ss19.service.movie;

import com.ss19.entity.Movie;

import java.util.List;

public interface MovieService{
    List<Movie> findAll();
    Movie findById(Long id);
    void save(Movie movie);
    void update(Movie movie);
    void deleteById(Long id);
}
