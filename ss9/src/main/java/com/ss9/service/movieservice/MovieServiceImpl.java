package com.ss9.service.movieservice;

import com.ss9.model.Movie;
import com.ss9.repository.movierepository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService{
    @Autowired
    private MovieRepository movieRepository;

    @Override
    public List<Movie> findAll(){
        return movieRepository.findAll();
    }

    @Override
    public Movie findById(Long id){
        return movieRepository.findById(id);
    }
}
