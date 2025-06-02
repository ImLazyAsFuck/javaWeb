package com.ss16.controller;

import com.ss16.model.user.User;
import com.ss16.service.bustrip.BustripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping
public class TripController{
    @Autowired
    private BustripService bustripService;

    @GetMapping
    public String list(Model model) {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String listTrips(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/login";
        model.addAttribute("trips", bustripService.findAll());
        return "user/home";
    }
}
