package com.hackathon.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.hackathon.dto.ProductDTO;
import com.hackathon.model.Product;
import com.hackathon.model.ProductStatus;
import com.hackathon.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/product")
public class ProductController{
    @Autowired
    private ProductService productService;

    @Autowired
    private Cloudinary cloudinary;

    @GetMapping
    public String list(@RequestParam(value = "name", required = false) String name, Model model) {
        if (name != null && !name.isEmpty()) {
            model.addAttribute("products", productService.findProductByName(name));
        } else {
            model.addAttribute("products", productService.getAllProducts());
        }
        return "product_list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("product") ProductDTO product, Model model){
        model.addAttribute("product", product);
        return "add_product";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model){
        model.addAttribute("product", productService.getProductById(id));
        return "edit_product";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id){
        productService.deleteProduct(id);
        return "redirect:/product";
    }

    @PostMapping("/add")
    public String save(@Valid @ModelAttribute("product") ProductDTO product, BindingResult result, Model model) throws IOException{
        if(result.hasErrors()){
            return "add_product";
        }
        Map<String, Object> imgResult = cloudinary.uploader().upload(product.getImage().getBytes(), ObjectUtils.emptyMap());
        product.setImg(imgResult.get("url").toString());
        productService.createProduct(new Product(product.getId(), product.getName(), product.getPrice(), product.getImg(), product.getDescription(), ProductStatus.valueOf(product.getStatus()), null));
        return "redirect:/product";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable("id") int id, @Valid @ModelAttribute("product") ProductDTO product, BindingResult result, Model model) throws IOException{
        if(result.hasErrors()){
            return "edit_product";
        }
        Map<String, Object> imgResult = cloudinary.uploader().upload(product.getImage().getBytes(), ObjectUtils.emptyMap());
        product.setImg(imgResult.get("url").toString());
        productService.updateProduct(new Product(product.getId(), product.getName(), product.getPrice(), product.getImg(), product.getDescription(), ProductStatus.valueOf(product.getStatus()), null));
        return "redirect:/product";
    }
}
