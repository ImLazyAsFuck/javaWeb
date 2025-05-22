package com.ss11.repository;

import com.ss11.model.Movie;

import java.util.List;

public interface MovieDAO {
    List<Movie> getAll();
    Movie get(int id);
    void save(Movie movie);
    void update(Movie movie);
    void delete(int id);
}

