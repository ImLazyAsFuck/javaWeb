package com.ss9.controller;

import com.ss9.service.movieservice.MovieService;
import com.ss9.service.scheduleservice.ScheduleService;
import com.ss9.service.seatservice.SeatService;
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
    @Autowired
    ScheduleService scheduleService;

    @Autowired
    SeatService seatService;

    @GetMapping("/home")
    public ModelAndView home(){
        return new ModelAndView("home")
                .addObject("movies", movieService.findAll());
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable Long id){
        return new ModelAndView("detail_movie")
                .addObject("movie", movieService.findById(id))
                .addObject("schedules", scheduleService.findAllByMovieId(id));
    }

    @GetMapping("/schedule/{id}")
    public ModelAndView schedule(@PathVariable Long id){
        return new ModelAndView("schedule")
                .addObject("schedule", scheduleService.findById(id))
                .addObject("movie", movieService.findById(scheduleService.findById(id).getMovieId()));
//                .addObject("seats", seatService.);
    }
}
