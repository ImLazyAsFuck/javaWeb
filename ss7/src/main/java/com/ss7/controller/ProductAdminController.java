package com.ss7.controller;

import com.ss7.model.Category;
import com.ss7.model.Product;
import com.ss7.service.CategoryService;
import com.ss7.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/products")
public class ProductAdminController {

    @Autowired
    private ProductService productService;
    
    @Autowired
    private CategoryService categoryService;

    @ModelAttribute("categories")
    public List<Category> getCategories() {
        return categoryService.findAll();
    }
    

    @GetMapping("/list")
    public String listProducts(
            @RequestParam(required = false) String keyword,
            Model model) {
        
        List<Product> products = productService.findAll();
        

        if (keyword != null && !keyword.trim().isEmpty()) {
            String searchTerm = keyword.toLowerCase();
            products = products.stream()
                    .filter(p -> p.getName().toLowerCase().contains(searchTerm) 
                            || p.getDescription().toLowerCase().contains(searchTerm))
                    .toList();
            model.addAttribute("keyword", keyword);
        }
        
        model.addAttribute("products", products);
        return "admin/productList";
    }
    

    @GetMapping("/view/{id}")
    public String showProductDetails(@PathVariable("id") int id, Model model, RedirectAttributes redirectAttributes) {
        Product product = productService.findById(id);
        
        if (product == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Product not found");
            return "redirect:/admin/products/list";
        }
        

        Category category = categoryService.findById(product.getCategoryId());
        model.addAttribute("product", product);
        model.addAttribute("category", category);
        
        return "admin/productDetails";
    }
    

    @GetMapping("/add")
    public String addProduct(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("isNewProduct", true);
        return "admin/productForm";
    }
    

    @PostMapping("/save")
    public String saveProduct(
            @ModelAttribute Product product,
            RedirectAttributes redirectAttributes) {
        
        boolean success;
        
        if (product.getId() > 0) {

            success = productService.update(product);
            if (success) {
                redirectAttributes.addFlashAttribute("successMessage", 
                        "Product '" + product.getName() + "' was updated successfully");
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", 
                        "Failed to update product '" + product.getName() + "'");
            }
        } else {

            success = productService.save(product);
            if (success) {
                redirectAttributes.addFlashAttribute("successMessage", 
                        "Product '" + product.getName() + "' was added successfully");
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", 
                        "Failed to add product '" + product.getName() + "'");
            }
        }
        
        return "redirect:/admin/products/list";
    }
    

    @GetMapping("/edit/{id}")
    public String editProduct(
            @PathVariable("id") int id,
            Model model,
            RedirectAttributes redirectAttributes) {
        
        Product product = productService.findById(id);
        
        if (product == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Product not found");
            return "redirect:/admin/products/list";
        }
        
        model.addAttribute("product", product);
        model.addAttribute("isNewProduct", false);
        return "admin/productForm";
    }
    

    @GetMapping("/delete/{id}")
    public String deleteProduct(
            @PathVariable("id") int id,
            RedirectAttributes redirectAttributes) {
        
        Product product = productService.findById(id);
        
        if (product == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Product not found");
            return "redirect:/admin/products/list";
        }
        
        boolean success = productService.deleteById(id);
        
        if (success) {
            redirectAttributes.addFlashAttribute("successMessage", 
                    "Product '" + product.getName() + "' was deleted successfully");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", 
                    "Failed to delete product '" + product.getName() + "'");
        }
        
        return "redirect:/admin/products/list";
    }
}
