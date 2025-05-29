package com.ss14.controller;

import com.ss14.model.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @GetMapping
    public String listOrders(HttpSession session, Model model) {
        List<Order> orders = getOrdersFromSession(session);
        model.addAttribute("orders", orders);
        return "b5_8/order_list";
    }

    @GetMapping("/view")
    public String viewOrder(@RequestParam int id, HttpSession session, Model model) {
        List<Order> orders = getOrdersFromSession(session);
        Order order = null;
        for (Order o : orders) {
            if (o.getOrderId().equals(String.valueOf(id))) {
                order = o;
                break;
            }
        }
        if (order == null) {
            model.addAttribute("error", "Không tìm thấy đơn hàng với ID: " + id);
            return "b5_8/order_list";
        }
        model.addAttribute("order", order);
        return "b5_8/order_view";
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("order", new Order());
        return "b5_8/order_form";
    }

    @PostMapping("/save")
    public String saveOrder(@ModelAttribute Order order, HttpSession session, RedirectAttributes redirectAttributes) {
        List<Order> orders = getOrdersFromSession(session);

        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getOrderId().equals(order.getOrderId())) {
                orders.get(i).setUsername(order.getUsername());
                orders.get(i).setProductName(order.getProductName());
                orders.get(i).setQuantity(order.getQuantity());
                session.setAttribute("orders", orders);
                redirectAttributes.addFlashAttribute("message", "Cập nhật đơn hàng thành công!");
                return "redirect:/orders";
            }
        }

        orders.add(order);
        session.setAttribute("orders", orders);
        redirectAttributes.addFlashAttribute("message", "Tạo đơn hàng mới thành công!");
        return "redirect:/orders";
    }

    @GetMapping("/edit")
    public String editOrder(@RequestParam("id") String orderId, HttpSession session, Model model) {
        List<Order> orders = getOrdersFromSession(session);
        for (Order o : orders) {
            if (o.getOrderId().equals(orderId)) {
                model.addAttribute("order", o);
                return "b5_8/order_form";
            }
        }
        return "redirect:/orders";
    }

    @GetMapping("/delete")
    public String deleteOrder(@RequestParam("id") String orderId, HttpSession session) {
        List<Order> orders = getOrdersFromSession(session);
        orders.removeIf(o -> o.getOrderId().equals(orderId));
        session.setAttribute("orders", orders);
        return "redirect:/orders";
    }

    private List<Order> getOrdersFromSession(HttpSession session) {
        List<Order> orders = (List<Order>) session.getAttribute("orders");
        if (orders == null) {
            orders = new ArrayList<>();
            session.setAttribute("orders", orders);
        }
        return orders;
    }
}
