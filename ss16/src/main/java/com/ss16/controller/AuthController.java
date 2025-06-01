package com.ss16.controller;

import com.ss16.dto.auth.LoginDto;
import com.ss16.dto.auth.RegisterDto;
import com.ss16.model.user.User;
import com.ss16.model.user.UserRole;
import com.ss16.model.user.UserStatus;
import com.ss16.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class AuthController{
    @Autowired
    private AuthService authService;

    @GetMapping("/login")
    public String login(Model model, HttpSession session){
        model.addAttribute("user", new LoginDto());
        return "auth/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user", new RegisterDto());
        return "auth/register";
    }

    @PostMapping("/login")
    public String loginSubmit(@Valid @ModelAttribute("user") LoginDto loginDto, BindingResult bindingResult, Model model, HttpSession session){
        if(bindingResult.hasErrors()) return "auth/login";
        User user = authService.get(loginDto.getUsername(), loginDto.getPassword());
        if(user == null){
            model.addAttribute("error", "Invalid username or password");
            return "auth/login";
        };
        if(user.getStatus() != UserStatus.ACTIVE){
            model.addAttribute("error", "Your account is not active yet");
            return "auth/login";
        }
        if(user.getRole() == UserRole.ADMIN){
            session.setAttribute("user", user);
            return "redirect:/admin/home";
        }
        session.setAttribute("user", user);
        return "redirect:/home";
    }

    @PostMapping("/register")
    public String registerSubmit(@Valid @ModelAttribute("user") RegisterDto registerDto, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()) return "auth/register";
        authService.save(new User(registerDto.getId() ,registerDto.getUsername(), registerDto.getPassword(), registerDto.getEmail(), UserRole.USER, UserStatus.ACTIVE));
        return "redirect:/login";
    }
}
