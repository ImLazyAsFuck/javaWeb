package com.ss9.controller;

import com.ss9.service.movieservice.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class HomeController{

    @Autowired
    MovieService movieService;

    @GetMapping("/home")
    public ModelAndView home(){
        return new ModelAndView("home")
                .addObject("movies", movieService.findAll());
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable Long id){
        return new ModelAndView("detail_movie")
                .addObject("movie", movieService.findById(id));
    }
}
