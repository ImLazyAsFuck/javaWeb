package com.ss17.controller;

import com.ss17.entity.Customer;
import com.ss17.entity.Product;
import com.ss17.entity.ProductCart;
import com.ss17.service.product.ProductService;
import com.ss17.service.productcart.ProductCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
public class HomeController{
    @Autowired
    HttpSession session;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductCartService productCartService;

    @GetMapping("/home")
    public String home(Model model,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "5") int size) {
        if(session.getAttribute("customer") == null){
            return "redirect:/login";
        }
        List<Product> products = productService.findAll(page, size);
        long totalProducts = productService.countAll();
        int totalPages = (int) Math.ceil((double) totalProducts / size);

        model.addAttribute("products", products);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        return "user/home";
    }

    @GetMapping("/product/{id}")
    public String product(@PathVariable int id, Model model){
        if(session.getAttribute("customer") == null){
            return "redirect:/login";
        }
        Product product = productService.findById(id);
        if(product == null){
            return "redirect:/user/home";
        }
        model.addAttribute("product", product);
        return "user/detail";
    }

    @PostMapping("/product/{id}")
    public String addToCart(@PathVariable int id) {
        Product product = productService.findById(id);
        Customer customer = (Customer) session.getAttribute("customer");

        if (customer == null || product == null) {
            return "redirect:/login";
        }

        int customerId = customer.getId();
        int productId = product.getId();

        ProductCart existingCart = productCartService.findByCustomerIdAndProductId(customerId, productId);

        if (existingCart != null) {
            existingCart.setQuantity(existingCart.getQuantity() + 1);
            productCartService.update(existingCart);
        } else {
            ProductCart productCart = new ProductCart();
            productCart.setCustomerId(customerId);
            productCart.setProductId(productId);
            productCart.setQuantity(1);
            productCartService.save(productCart);
        }

        return "redirect:/user/home";
    }

}
