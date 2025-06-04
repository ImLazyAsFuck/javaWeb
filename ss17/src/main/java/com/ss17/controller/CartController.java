package com.ss17.controller;

import com.ss17.entity.Customer;
import com.ss17.entity.Order;
import com.ss17.entity.OrderDetail;
import com.ss17.entity.ProductCart;
import com.ss17.model.OrderStatus;
import com.ss17.service.order.OrderService;
import com.ss17.service.product.ProductService;
import com.ss17.service.productcart.ProductCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private ProductCartService productCartService;
    @Autowired
    private ProductService productService;
    @Autowired
    private HttpSession session;

    @Autowired
    private OrderService orderService;

    @GetMapping
    public String cart(Model model){
        Customer customer = (Customer) session.getAttribute("customer");
        if(customer == null){
            return "redirect:/login";
        }
        model.addAttribute("productCarts", productCartService.findByCustomerId(customer.getId()));
        return "user/cart";
    }

    @PostMapping("/increase/{id}")
    public String increaseCart(@PathVariable int id) {
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer == null) {
            return "redirect:/login";
        }

        ProductCart productCart = productCartService.findByCustomerIdAndProductId(customer.getId(), id);
        if (productCart == null) {
            return "redirect:/home";
        }

        int stock = productCart.getProduct().getStock();
        if (stock > 0) {
            productCart.setQuantity(productCart.getQuantity() + 1);
            productCart.getProduct().setStock(stock - 1);
            productCartService.save(productCart);
        }

        return "redirect:/cart";
    }

    @PostMapping("/decrease/{id}")
    public String decreaseCart(@PathVariable int id) {
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer == null) {
            return "redirect:/login";
        }

        ProductCart productCart = productCartService.findByCustomerIdAndProductId(customer.getId(), id);
        if (productCart == null) {
            return "redirect:/home";
        }

        if (productCart.getQuantity() > 1) {
            productCart.setQuantity(productCart.getQuantity() - 1);
            productCart.getProduct().setStock(productCart.getProduct().getStock() + 1); // tăng stock
            productCartService.save(productCart);
        }

        return "redirect:/cart";
    }


    @PostMapping("/delete/{id}")
    public String deleteCart(@PathVariable int id) {
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer == null) {
            return "redirect:/login";
        }
        productCartService.delete(productCartService.findByCustomerIdAndProductId(customer.getId(), id));
        return "redirect:/cart";
    }

    @GetMapping("/checkout")
    public String checkout(Model model, HttpSession session) {
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer == null) {
            return "redirect:/login";
        }

        List<ProductCart> cart = productCartService.findByCustomerId(customer.getId());
        if (cart == null || cart.isEmpty()) {
            model.addAttribute("error", "Giỏ hàng trống!");
            return "user/cart";
        }
        double total = cart.stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();

        model.addAttribute("total", total);


        model.addAttribute("cartItems", cart);
        model.addAttribute("recipientName", "");
        model.addAttribute("phoneNumber", "");
        model.addAttribute("address", "");
        model.addAttribute("total", total);
        return "user/checkout";
    }


    @PostMapping("/checkout")
    public String checkout(@RequestParam String recipientName,
                           @RequestParam String phoneNumber,
                           @RequestParam String address,
                           HttpSession session,
                           RedirectAttributes redirectAttributes) {

        Customer customer = (Customer) session.getAttribute("customer");
        if (customer == null) {
            return "redirect:/login";
        }

        List<ProductCart> cart = productCartService.findByCustomerId(customer.getId());
        if (cart == null || cart.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Giỏ hàng trống!");
            return "redirect:/cart";
        }

        Order order = new Order();
        order.setCustomer(customer);
        order.setRecipientName(recipientName);
        order.setPhoneNumber(phoneNumber);
        order.setAddress(address);
        order.setStatus(OrderStatus.PENDING);

        double total = 0;
        List<OrderDetail> details = new ArrayList<>();
        for (ProductCart item : cart) {
            OrderDetail detail = new OrderDetail();
            detail.setOrder(order);
            detail.setProduct(item.getProduct());
            detail.setQuantity(item.getQuantity());
            detail.setUnitPrice(item.getProduct().getPrice());
            total += item.getProduct().getPrice() * item.getQuantity();
            details.add(detail);
        }

        order.setOrderDetails(details);
        order.setTotalMoney(total);

        orderService.save(order);
        productCartService.deleteCartToEmpty(customer.getId());

        redirectAttributes.addFlashAttribute("message", "Đặt hàng thành công!");
        return "redirect:/user/home";
    }


}
