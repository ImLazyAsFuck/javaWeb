package com.ss17.controller;

import com.ss17.entity.Customer;
import com.ss17.entity.Order;
import com.ss17.model.OrderStatus;
import com.ss17.service.customer.CustomerService;
import com.ss17.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/profile")
public class ProfileController{
    @Autowired
    private CustomerService customerService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private HttpSession session;

    @GetMapping
    public String profilePage(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size,
            HttpSession session,
            Model model) {

        Customer customer = (Customer) session.getAttribute("customer");
        if (customer == null) {
            return "redirect:/login";
        }

        if (page < 1) page = 1;


        List<Order> orders = orderService.findByCustomerId(customer.getId(), page, size);
        int totalOrders = orderService.countByCustomerId(customer.getId());
        int totalPages = (int) Math.ceil((double) totalOrders / size);

        model.addAttribute("customer", customer);
        model.addAttribute("orders", orders);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        return "user/profile";
    }


    @PostMapping("/cancel-order")
    public String cancelOrder(@RequestParam int orderId, HttpSession session) {
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer == null) return "redirect:/login";

        Order order = orderService.findById(orderId);
        if (order != null && order.getCustomer().getId() == customer.getId()) {
            order.setStatus(OrderStatus.CANCELLED);
            orderService.update(order);
        }

        return "redirect:/profile";
    }

    @PostMapping("/update")
    public String updateProfile(@ModelAttribute Customer customerForm, HttpSession session) {
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer == null) return "redirect:/login";

        customerForm.setId(customer.getId());
        customerService.update(customerForm);
        session.setAttribute("customer", customerForm);

        return "redirect:/profile";
    }
}
