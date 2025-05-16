package com.ss9.controller;

import com.ss9.model.Schedule;
import com.ss9.service.scheduleservice.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping
    public ModelAndView showSchedule() {
        List<Schedule> schedules = scheduleService.findAll();
        System.out.println("GET /schedule - Found " + schedules.size() + " schedules");
        return new ModelAndView("schedule")
                .addObject("schedules", schedules);
    }

    @PostMapping
    public ModelAndView showScheduleByTitle(@RequestParam String title) {
        System.out.println("POST /schedule - Searching for title: " + title);
        ModelAndView mv = new ModelAndView("schedule");
        
        if (title != null && !title.isEmpty()) {
            List<Schedule> schedules = scheduleService.findAllScheduleByMovie(title);
            System.out.println("Found " + schedules.size() + " schedules for movie: " + title);
            mv.addObject("schedules", schedules);
        } else {
            List<Schedule> schedules = scheduleService.findAll();
            System.out.println("Title empty, returning all " + schedules.size() + " schedules");
            mv.addObject("schedules", schedules);
        }
        return mv;
    }
}


