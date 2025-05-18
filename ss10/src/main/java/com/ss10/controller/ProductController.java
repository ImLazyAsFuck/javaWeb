package com.ss10.controller;

import com.ss10.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController{
    private static Product currentProduct = null;

    @GetMapping
    public String product(Model model){
        model.addAttribute("product", new Product());
        return "product/productForm";
    }

    @PostMapping
    public String saveProduct(@ModelAttribute Product product){
        currentProduct = product;
        return "redirect:/product/result";
    }

    @GetMapping("/result")
    public String result(Model model){
        model.addAttribute("product", currentProduct);
        return "product/productResult";
    }
}
