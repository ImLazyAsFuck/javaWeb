package com.ss17.controller;

import com.cloudinary.Cloudinary;
import com.ss17.dto.ProductDto;
import com.ss17.entity.Customer;
import com.ss17.entity.Product;
import com.ss17.model.CustomerRole;
import com.ss17.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin/product")
public class ProductController{
    @Autowired
    private HttpSession session;
    @Autowired
    private ProductService productService;
    @Autowired
    private Cloudinary cloudinary;

    @GetMapping
    public String list(@RequestParam(defaultValue = "1") int page,
                       @RequestParam(required = false) String search,
                       @RequestParam(required = false) String priceRange,
                       Model model,
                       HttpSession session) {
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer == null || customer.getRole() != CustomerRole.ADMIN) {
            return "redirect:/login";
        }

        int size = 5;
        long totalItems;
        List<Product> pageProducts;
        Double minPrice = null;
        Double maxPrice = null;

        if (priceRange != null && !priceRange.isEmpty()) {
            switch (priceRange) {
                case "0-500":
                    minPrice = 0.0;
                    maxPrice = 500.0;
                    break;
                case "500-1000":
                    minPrice = 500.0;
                    maxPrice = 1000.0;
                    break;
                case "1000-2000":
                    minPrice = 1000.0;
                    maxPrice = 2000.0;
                    break;
                case "2000+":
                    minPrice = 2000.0;
                    maxPrice = null;
                    break;
            }
        }

        if (search != null && !search.trim().isEmpty()) {
            if (minPrice != null || maxPrice != null) {
                totalItems = productService.countByNameAndPrice(search, minPrice, maxPrice);
                pageProducts = productService.findByNameAndPrice(search, minPrice, maxPrice, page, size);
            } else {
                totalItems = productService.countByName(search);
                pageProducts = productService.findByName(search, page, size);
            }
        } else {
            if (minPrice != null || maxPrice != null) {
                totalItems = productService.countByPrice(minPrice, maxPrice);
                pageProducts = productService.findByPrice(minPrice, maxPrice, page, size);
            } else {
                totalItems = productService.countAll();
                pageProducts = productService.findAll(page, size);
            }
        }

        int totalPages = (int) Math.ceil((double) totalItems / size);
        if (page < 1) page = 1;
        if (page > totalPages && totalPages > 0) page = totalPages;

        model.addAttribute("products", pageProducts);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("search", search);
        model.addAttribute("priceRange", priceRange);

        return "admin/product/product_list";
    }


    @GetMapping("/add")
    public String addProduct(Model model, ProductDto productDto) {
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer == null || customer.getRole() != CustomerRole.ADMIN) {
            return "redirect:/login";
        }
        model.addAttribute("product", productDto);
        return "admin/product/add_product";
    }

    @GetMapping("/edit/{id}")
    public String editProduct(Model model, @PathVariable int id) {
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer == null || customer.getRole() != CustomerRole.ADMIN) {
            return "redirect:/login";
        }
        Product product = productService.findById(id);
        if(product == null){
            return "redirect:/login";
        }
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setProductName(product.getProductName());
        productDto.setPrice(product.getPrice());
        productDto.setStock(product.getStock());
        productDto.setDescription(product.getDescription());
        productDto.setImage(product.getImage());
        model.addAttribute("product", productDto);
        return "admin/product/edit_product";
    }

    @PostMapping("/add")
    public String saveProduct(Model model, @Valid @ModelAttribute ProductDto productDto, BindingResult bindingResult) throws IOException{
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer == null || customer.getRole() != CustomerRole.ADMIN) {
            return "redirect:/login";
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("product", productDto);
            return "admin/product/add_product";
        }

        if(productDto.getFile() != null && !productDto.getFile().isEmpty()){
            String url = cloudinary.uploader().upload(productDto.getFile().getBytes(), null).get("url").toString();
            productDto.setImage(url);
        }

        Product product = new Product();
        product.setProductName(productDto.getProductName());
        product.setPrice(productDto.getPrice());
        product.setStock(productDto.getStock());
        product.setDescription(productDto.getDescription());
        product.setImage(productDto.getImage());
        productService.save(product);
        return "redirect:/admin/product";
    }

    @PostMapping("/edit/{id}")
    public String editProduct(@PathVariable int id ,Model model, @Valid @ModelAttribute ProductDto productDto, BindingResult bindingResult) throws IOException {
        Customer customer = (Customer) session.getAttribute("customer");
        Product product = productService.findById(id);
        if (customer == null || customer.getRole() != CustomerRole.ADMIN || product == null) {
            return "redirect:/login";
        }

        if (bindingResult.hasErrors()) {
//            model.addAttribute("product", productDto);
            return "admin/product/edit_product";
        }

        if(productDto.getFile() != null && !productDto.getFile().isEmpty()){
            String url = cloudinary.uploader().upload(productDto.getFile().getBytes(), null).get("url").toString();
            productDto.setImage(url);
            product.setImage(productDto.getImage());
        }

        product.setProductName(productDto.getProductName());
        product.setPrice(productDto.getPrice());
        product.setStock(productDto.getStock());
        product.setDescription(productDto.getDescription());
        productService.update(product);
        return "redirect:/admin/product";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(Model model, @PathVariable int id) {
        Product product = productService.findById(id);
        if (product == null) {
            return "redirect:/login";
        }
        productService.delete(product);
        return "redirect:/admin/product";
    }
}
