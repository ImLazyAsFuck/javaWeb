package com.ss17.controller;

import com.ss17.entity.Customer;
import com.ss17.entity.Product;
import com.ss17.model.CustomerRole;
import com.ss17.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/customer")
public class CustomerController{
    @Autowired
    private CustomerService customerService;
    @Autowired
    private HttpSession session;

    @GetMapping
    public String getCustomers(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(required = false) String name,
            Model model) {
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer == null) {
            return "redirect:/login";
        }

        int size = 5;
        long totalItems;
        List<Customer> customers;

        if (name != null && !name.isEmpty()) {
            customers = customerService.findByName(name, page, size);
            totalItems = customerService.countCByName(name);
        } else {
            customers = customerService.findAll(page, size);
            totalItems = customerService.countCustomers();
        }

        long totalPages = (long) Math.ceil((double) totalItems / size);

        model.addAttribute("customers", customers);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);


        return "admin/customer/customer_list";
    }


    @PostMapping("status")
    public String updateStatus(@RequestParam int id, @RequestParam boolean status) {
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer == null) {
            return "redirect:/login";
        }
        Customer updateCustomer = customerService.findById(id);
        if(updateCustomer == null){
            return "redirect:/admin/customer";
        }
        updateCustomer.setStatus(status);
        customerService.save(updateCustomer);
        return "redirect:/admin/customer";
    }
}
