package com.ss15.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
public class PageController{
    @GetMapping
    public String home(Model model){
        model.addAttribute("title", "Home");
        return "b4/home";
    }

    @GetMapping("/about")
    public String about(Model model){
        model.addAttribute("title", "About");
        return "b4/about";
    }

    @GetMapping("/contact")
    public String contact(Model model){
        model.addAttribute("title", "Contact");
        return "b4/contact";
    }
}
