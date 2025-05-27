package com.demo.controller;

import com.demo.model.Product;
import com.demo.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/product")
public class ProductController{
    @Autowired
    private ProductService productService;

    @GetMapping
    public String list(Model model){
        model.addAttribute("products", productService.getAllProducts());
        return "b2/product_list";
    }

    @GetMapping("add")
    public String add(Model model){
        model.addAttribute("product", new Product());
        return "b2/add_product";
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable int id, Model model){
        Product p = productService.getProductById(id);
        if(p == null){
            return "redirect:/product";
        }
        model.addAttribute("product", p);
        return "b2/edit_product";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable int id){
        productService.deleteProduct(id);
        return "redirect:/product";
    }

    @PostMapping("add")
    public String save(@Valid @ModelAttribute Product product, BindingResult result){
        if(result.hasErrors()){
            return "b2/add_product";
        }
        productService.createProduct(product);
        return "redirect:/product";
    }

    @PostMapping("edit")
    public String update(@Valid @ModelAttribute Product product, BindingResult result){
        if(result.hasErrors()){
            return "b2/edit_product";
        }
        productService.updateProduct(product);
        return "redirect:/product";
    }
}
