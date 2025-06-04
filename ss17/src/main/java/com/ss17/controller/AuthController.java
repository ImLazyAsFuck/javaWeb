package com.ss17.controller;

import com.ss17.dto.LoginDto;
import com.ss17.dto.RegisterDto;
import com.ss17.entity.Customer;
import com.ss17.model.CustomerRole;
import com.ss17.service.customer.CustomerService;
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
    private CustomerService customerService;

    @Autowired
    HttpSession session;

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("registerDto", new RegisterDto());
        return "auth/register";
    }

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("loginDto", new LoginDto());
        return "auth/login";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute RegisterDto registerDto, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("registerDto", registerDto);
            return "auth/register";
        }
        Customer customer = new Customer(
                0,
                registerDto.getUsername(),
                registerDto.getPassword(),
                registerDto.getEmail(),
                registerDto.getPhone(),
                CustomerRole.USER,
                true
        );
        customerService.save(customer);
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginDto loginDto,
                        BindingResult bindingResult,
                        Model model,
                        HttpSession session) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("loginDto", loginDto);
            return "auth/login";
        }

        Customer customer = customerService.login(loginDto.getUsername(), loginDto.getPassword());

        if (customer == null) {
            model.addAttribute("wrong", "Wrong username or password");
            model.addAttribute("loginDto", loginDto);
            return "auth/login";
        }
        session.setAttribute("customer", customer);
        if(customer.getRole() == CustomerRole.ADMIN){
            return "redirect:/admin/home";
        }
        return "redirect:/user/home";
    }


    @GetMapping("/logout")
    public String logout(){
        session.removeAttribute("customer");
        return "redirect:/login";
    }

}
