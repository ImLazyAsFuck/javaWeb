package com.ss17.controller;

import com.ss17.entity.Customer;
import com.ss17.service.customer.CustomerService;
import com.ss17.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class DashboardController{
    @Autowired
    private ProductService productService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private HttpSession session;

    @GetMapping
    public String dashboard(){
        return "redirect:/admin/home";
    }

    @GetMapping("/home")
    public String adminHome(Model model){
        Customer customer = (Customer) session.getAttribute("customer");
        if(customer == null){
            return "redirect:/login";
        }
        model.addAttribute("customers", customerService.findAll().size());
        model.addAttribute("products", productService.countAll());
        return "admin/home";
    }
}
