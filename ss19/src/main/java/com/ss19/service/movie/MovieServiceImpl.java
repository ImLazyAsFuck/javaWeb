package com.ss19.service.movie;

import com.ss19.entity.Movie;
import com.ss19.repo.movie.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService{
    @Autowired
    private MovieRepo movieRepo;

    @Override
    public List<Movie> findAll(){
        return movieRepo.findAll();
    }

    @Override
    public Movie findById(Long id){
        return movieRepo.findById(id);
    }

    @Override
    public void save(Movie movie){
        movieRepo.save(movie);
    }

    @Override
    public void update(Movie movie){
         movieRepo.update(movie);
    }

    @Override
    public void deleteById(Long id){
        movieRepo.deleteById(id);
    }
}
