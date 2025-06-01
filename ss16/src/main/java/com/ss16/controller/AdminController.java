package com.ss16.controller;

import com.ss16.model.user.User;
import com.ss16.model.user.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController{
    @Autowired
    private AuthController authController;
    @Autowired
    private HttpSession session;

    @RequestMapping("/home")
    public String home(){
        if(session.getAttribute("user") == null) return "redirect:/login";
        User user = (User) session.getAttribute("user");
        if(user.getRole() != UserRole.ADMIN) return "redirect:/login";
        return "admin/home";
    }

    @GetMapping
    public String admin(){
        return "redirect:/admin/home";
    }
}
