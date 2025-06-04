package com.ss17.controller;

import com.ss17.entity.Customer;
import com.ss17.entity.Order;
import com.ss17.model.CustomerRole;
import com.ss17.model.OrderStatus;
import com.ss17.service.customer.CustomerService;
import com.ss17.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

    @Controller
    @RequestMapping("/admin/order")
    public class OrderController {

        @Autowired
        private OrderService orderService;

        @Autowired
        private HttpSession session;

        @GetMapping
        public String order(
                @RequestParam(required = false) String receiverName,
                @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date orderDate,
                @RequestParam(required = false) String status,
                @RequestParam(defaultValue = "0") int page,
                @RequestParam(defaultValue = "10") int size,
                Model model) {

            Customer customer = (Customer) session.getAttribute("customer");
            if (customer == null || customer.getRole() != CustomerRole.ADMIN) {
                return "redirect:/login";
            }

            OrderStatus orderStatus = null;
            if (status != null && !status.isEmpty()) {
                try {
                    orderStatus = OrderStatus.valueOf(status);
                } catch (Exception e) {
                    model.addAttribute("error", "Trạng thái không hợp lệ");
                }
            }

            List<Order> orders = orderService.searchOrders(receiverName, orderDate, orderStatus, page, size);
            int totalOrders = orderService.countSearchOrders(receiverName, orderDate, orderStatus);

            model.addAttribute("orders", orders);
            model.addAttribute("receiverName", receiverName);
            model.addAttribute("orderDate", orderDate);
            model.addAttribute("status", status);
            model.addAttribute("orderStatuses", OrderStatus.values());
            model.addAttribute("currentPage", page);
            model.addAttribute("totalOrders", totalOrders);
            model.addAttribute("pageSize", size);

            return "admin/order/order_list";
        }


        @PostMapping("/{id}/confirm")
        public String confirmOrder(@PathVariable int id) {
            updateOrderStatus(id, OrderStatus.DELIVERED);
            return "redirect:/admin/order";
        }

        @PostMapping("/{id}/cancel")
        public String cancelOrder(@PathVariable int id) {
            updateOrderStatus(id, OrderStatus.CANCELLED);
            return "redirect:/admin/order";
        }

        @PostMapping("/{id}/next")
        public String nextStatus(@PathVariable int id) {
            moveToNextStatus(id);
            return "redirect:/admin/order";
        }

        public void updateOrderStatus(int id, OrderStatus status) {
            Order order = orderService.findById(id);
            if (order != null) {
                order.setStatus(status);
            }
            order.setStatus(status);
            orderService.save(order);
        }

        public void moveToNextStatus(int id) {
            Order order = orderService.findById(id);
            if (order != null) {
                order.setStatus(OrderStatus.DELIVERED);
            }
            OrderStatus current = order.getStatus();

            switch(current){
                case PENDING:
                    order.setStatus(OrderStatus.CONFIRMED);
                    break;
                case CONFIRMED:
                    order.setStatus(OrderStatus.SHIPPING);
                    break;
                case SHIPPING:
                    order.setStatus(OrderStatus.DELIVERED);
                    break;
                default:
                    break;
            }

            orderService.save(order);
        }

    }

