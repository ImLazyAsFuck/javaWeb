package com.ss9.controller;

import com.ss9.model.Customer;
import com.ss9.service.loginservice.LoginService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @PostMapping("/login")
    public ModelAndView login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            HttpSession session
    ) {
        ModelAndView mv = new ModelAndView();

        if (username.isEmpty()) {
            mv.addObject("errorUsername", "Username cannot be empty");
            mv.setViewName("login");
            return mv;
        }

        if (password.isEmpty()) {
            mv.addObject("errorPassword", "Password cannot be empty");
            mv.setViewName("login");
            return mv;
        }

        Customer customer = new Customer();
        customer.setUsername(username);
        customer.setPassword(password);
        Customer result = loginService.login(customer);
        System.out.println("Login result: " + result);

        boolean isInvalid = (result == null || result.getUsername() == null || result.getPassword() == null);
        if (isInvalid) {
            mv.addObject("error", "Invalid username or password");
            mv.setViewName("login");
            return mv;
        }

        session.setAttribute("currentCustomer", result);
        mv.setViewName("redirect:/home");
        return mv;
    }

}

