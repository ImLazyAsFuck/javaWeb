package com.ss8.controller;

import com.ss8.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/registration")
public class RegistrationController{
    @GetMapping
    public String registration(){
        return "registration/registration";
    }

    @PostMapping
    public ModelAndView registration(User user) {
        ModelAndView mv = new ModelAndView();
        boolean hasError = false;

        if (user.getName() == null || user.getName().trim().isEmpty()) {
            mv.addObject("nameError", "Please enter your name");
            hasError = true;
        }

        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            mv.addObject("emailError", "Please enter your email");
            hasError = true;
        } else if (!user.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            mv.addObject("emailError", "Invalid email format");
            hasError = true;
        }

        if (user.getPhone() == null || user.getPhone().trim().isEmpty()) {
            mv.addObject("phoneError", "Please enter your phone number");
            hasError = true;
        } else if (!user.getPhone().matches("^0\\d{9}$")) {
            mv.addObject("phoneError", "Invalid phone number format (should be 10 digits, start with 0)");
            hasError = true;
        }

        if (hasError) {
            mv.setViewName("registration/registration");
        } else {
            mv.setViewName("registration/result");
            mv.addObject("user", user);
        }

        return mv;
    }

}
