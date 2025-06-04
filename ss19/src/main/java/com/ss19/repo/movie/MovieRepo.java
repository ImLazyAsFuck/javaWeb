package com.ss19.repo.movie;

import com.ss19.entity.Movie;

import java.util.List;

public interface MovieRepo{
    List<Movie> findAll();
    Movie findById(Long id);
    void save(Movie movie);
    void update(Movie movie);
    void deleteById(Long id);
}
