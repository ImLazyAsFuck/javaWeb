package com.ss9.service.movieservice;

import com.ss9.model.Movie;

import java.util.List;

public interface MovieService{
    List<Movie> findAll();
    Movie findById(Long id);
}
