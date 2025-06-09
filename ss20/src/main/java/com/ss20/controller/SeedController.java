package com.ss20.controller;

import com.ss20.service.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("seed")
public class SeedController{
    @Autowired
    private SeedService seedService;

    @GetMapping
    public String seed(){
        return "redirect:/seed/list";
    }

    @GetMapping("list")
    public String list(Model model){
        model.addAttribute("seeds", seedService.findAll());
        return "seed-list";
    }

    @GetMapping("add")
    public String add(){
        return "add_seed";
    }
}
