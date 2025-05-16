package com.ss9.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/not_found")
public class NotFoundController{

    @GetMapping("/404")
    public String notFound(){
        return "not_found";
    }
}
