package com.ss10.controller;

import com.ss10.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController{
    private static User currentUser = null;

    @GetMapping
    public ModelAndView user(){
        return new ModelAndView("user/userForm").addObject("user", new User());
    }

    @PostMapping
    public String saveUser(User user){
        currentUser = user;
        return "redirect:/user/result";
    }

    @GetMapping("/result")
    public ModelAndView result(){
        return new ModelAndView("user/userResult").addObject("user", currentUser);
    }
}
