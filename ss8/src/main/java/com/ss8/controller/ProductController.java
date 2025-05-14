package com.ss8.controller;

import com.ss8.model.Product;
import com.ss8.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/products")
public class ProductController{
    @Autowired
    ProductService productService;

    @GetMapping()
    public ModelAndView view(){
        ModelAndView mv = new ModelAndView("product/product_list");
        mv.addObject("products", productService.findAll());
        return mv;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        return new ModelAndView("product/add_product");
    }

    @PostMapping("/add")
    public ModelAndView add(Product product){
        productService.save(product);
        return new ModelAndView("redirect:/products");
    }
}
