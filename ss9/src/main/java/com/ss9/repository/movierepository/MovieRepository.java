package com.ss9.repository.movierepository;

import com.ss9.model.Movie;

import java.util.List;

public interface MovieRepository{
    List<Movie> findAll();
    Movie findById(Long id);
}
