package com.ss11.service.impl;

import com.ss11.model.Movie;
import com.ss11.repository.MovieDAO;
import com.ss11.service.MovieService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieDAO movieDAO;

    @Autowired
    private Cloudinary cloudinary;

    @Override
    @Transactional
    public List<Movie> getAllMovies() {
        return movieDAO.getAll();
    }

    @Override
    @Transactional
    public Movie getMovieById(int id) {
        return movieDAO.get(id);
    }

    @Override
    @Transactional
    public void saveMovie(Movie movie, String posterFile) {
        // Upload poster to Cloudinary if provided
        if (posterFile != null && !posterFile.isEmpty()) {
            try {
                Map uploadResult = cloudinary.uploader().upload(posterFile, ObjectUtils.asMap("resource_type", "image"));
                movie.setPoster((String) uploadResult.get("url"));
            } catch (Exception e) {
                throw new RuntimeException("Failed to upload poster to Cloudinary", e);
            }
        }

        movieDAO.save(movie);
    }

    @Override
    @Transactional
    public void updateMovie(Movie movie, String posterFile) {
        if (posterFile != null && !posterFile.isEmpty()) {
            try {
                Map uploadResult = cloudinary.uploader().upload(posterFile, ObjectUtils.asMap("resource_type", "image"));
                movie.setPoster((String) uploadResult.get("url"));
            } catch (Exception e) {
                throw new RuntimeException("Failed to upload poster to Cloudinary", e);
            }
        }

        movieDAO.update(movie);
    }

    @Override
    @Transactional
    public void deleteMovie(int id) {
        movieDAO.delete(id);
    }
}