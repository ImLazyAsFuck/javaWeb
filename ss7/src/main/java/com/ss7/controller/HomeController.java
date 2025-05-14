package com.ss7.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController{

    @RequestMapping("/home")
    public ModelAndView home(){
        return new ModelAndView("home", "welcome", "Welcome to Spring MVC!");
    }

    @RequestMapping("/greet")
    public String greet(@RequestParam String name){
        return "greet";
    }
}
