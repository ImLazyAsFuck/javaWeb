package com.ss11.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ss11.model.Movie;
import com.ss11.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private MovieService movieService;

    @GetMapping
    public String listMovies(Model model) {
        model.addAttribute("movies", movieService.getAllMovies());
        return "/b10/movies";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("movie", new Movie());
        return "/b10/movie-form";
    }

    @PostMapping("/save")
    public String saveMovie(@Valid @ModelAttribute("movie") Movie movie,
                            BindingResult bindingResult,
                            @RequestParam("posterFile") MultipartFile posterFile,
                            Model model) {
        if (bindingResult.hasErrors()) {
            return "/b10/movie-form";
        }

        if (!posterFile.isEmpty()) {
            String contentType = posterFile.getContentType();
            if (contentType == null || !contentType.startsWith("image/")) {
                model.addAttribute("errorMessage", "File poster phải là ảnh hợp lệ!");
                return "/b10/movie-form";
            }

            try {
                Map uploadResult = cloudinary.uploader().upload(posterFile.getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
                String imageUrl = (String) uploadResult.get("secure_url");
                movie.setPoster(imageUrl);
            } catch (IOException e) {
                model.addAttribute("errorMessage", "Lỗi khi upload poster lên Cloudinary!");
                return "/b10/movie-form";
            }
        }

        movieService.saveMovie(movie, movie.getPoster());
        return "redirect:/movies";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        Movie movie = movieService.getMovieById(id);
        if (movie == null) {
            model.addAttribute("errorMessage", "Movie not found");
            return "/b10/movies";
        }
        model.addAttribute("movie", movie);
        return "/b10/movie-form";
    }

    @PostMapping("/update")
    public String updateMovie(@Valid @ModelAttribute("movie") Movie movie,
                              BindingResult bindingResult,
                              @RequestParam("posterFile") MultipartFile posterFile,
                              Model model) {
        if (bindingResult.hasErrors()) {
            return "/b10/movie-form";
        }

        if (!posterFile.isEmpty()) {
            String contentType = posterFile.getContentType();
            if (contentType == null || !contentType.startsWith("image/")) {
                model.addAttribute("errorMessage", "File poster phải là ảnh hợp lệ!");
                return "/b10/movie-form";
            }

            try {
                Map uploadResult = cloudinary.uploader().upload(posterFile.getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
                String imageUrl = (String) uploadResult.get("secure_url");
                movie.setPoster(imageUrl);
            } catch (IOException e) {
                model.addAttribute("errorMessage", "Lỗi khi upload poster lên Cloudinary!");
                return "/b10/movie-form";
            }
        }

        movieService.updateMovie(movie, movie.getPoster());
        return "redirect:/movies";
    }

    @GetMapping("/delete/{id}")
    public String deleteMovie(@PathVariable("id") int id) {
        movieService.deleteMovie(id);
        return "redirect:/movies";
    }
}
