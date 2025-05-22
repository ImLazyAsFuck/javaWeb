package com.ss11.service;

import com.ss11.model.Movie;
import java.util.List;

public interface MovieService {
    List<Movie> getAllMovies();
    Movie getMovieById(int id);
    void saveMovie(Movie movie, String posterFile);
    void updateMovie(Movie movie, String posterFile);
    void deleteMovie(int id);
}