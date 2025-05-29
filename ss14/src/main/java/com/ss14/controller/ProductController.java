package com.ss14.controller;

import com.ss14.dto.ProductDTO;
import com.ss14.model.Cart;
import com.ss14.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @GetMapping("/list")
    public String getProductList(@CookieValue(value = "products", defaultValue = "") String products,
                                 Model model) {
        List<Product> productList = parseProductsFromCookie(products);
        model.addAttribute("products", productList);
        return "b2_4/products";
    }

    @GetMapping("/add")
    public String getAddProductForm(Model model){
        model.addAttribute("product", new ProductDTO());
        return "b2_4/add_product";
    }

    @PostMapping("/add")
    public String addProduct(@CookieValue(value = "products", defaultValue = "") String products,
                             Model model,
                             @Valid @ModelAttribute("product")
                             ProductDTO product,
                             BindingResult result,
                             HttpServletResponse response){
        if (result.hasErrors()) {
            model.addAttribute("product", product);
            return "b2_4/add_product";
        }
        int newId = 1;
        List<Product> productList = parseProductsFromCookie(products);
        for (Product p : productList) {
            newId = Math.max(newId, p.getId() + 1);
        }
        productList.add(new Product(newId, product.getName(), product.getPrice()));
        String cookieValue = buildCookieFromProducts(productList);
        String encodedValue = URLEncoder.encode(cookieValue, StandardCharsets.UTF_8);
        Cookie cookie = new Cookie("products", encodedValue);
        cookie.setMaxAge(7 * 24 * 60 * 60);
        cookie.setPath("/");
        response.addCookie(cookie);

        return "redirect:/product/list";
    }

    @GetMapping("/{id}")
    public String getProductDetail(@CookieValue(value = "products", defaultValue = "") String products, @PathVariable int id, Model model){
        List<Product> productList = parseProductsFromCookie(products);
        for (Product p : productList) {
            if (p.getId() == id) {
                model.addAttribute("product", p);
                return "b2_4/product";
            }
        }
        return "redirect:/product/list";
    }

    @PostMapping("/{id}")
    public String addToCart(@PathVariable int id, @CookieValue(value = "products", defaultValue = "") String products, @CookieValue(value = "carts", defaultValue = "") String carts, HttpServletResponse response, Model model){
        List<Product> productList = parseProductsFromCookie(products);
        List<Cart> cartList = parseCartsFromCookie(carts);
        for (Product p : productList) {
            if (p.getId() == id) {
                boolean found = false;
                for (Cart c : cartList) {
                    if (c.getProductId() == id) {
                        c.setQuantity(c.getQuantity() + 1);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    int newCartId = 1;
                    for (Cart c : cartList) {
                        newCartId = Math.max(newCartId, c.getId() + 1);
                    }
                    cartList.add(new Cart(newCartId, p.getId(), 1));
                }
                break;
            }
        }
        String cookieValue = buildCookieFromCarts(cartList);
        String encodedValue = URLEncoder.encode(cookieValue, StandardCharsets.UTF_8);
        Cookie cookie = new Cookie("carts", encodedValue);
        cookie.setMaxAge(7 * 24 * 60 * 60);
        cookie.setPath("/");
        response.addCookie(cookie);
        return "redirect:/product/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable int id,
                                @CookieValue(value = "products", defaultValue = "") String products,
                                HttpServletResponse response) {
        List<Product> productList = parseProductsFromCookie(products);
        for (Iterator<Product> iterator = productList.iterator(); iterator.hasNext();) {
            Product p = iterator.next();
            if (p.getId() == id) {
                iterator.remove();
                break;
            }
        }
        String cookieValue = buildCookieFromProducts(productList);
        String encodedValue = URLEncoder.encode(cookieValue, StandardCharsets.UTF_8);
        Cookie cookie = new Cookie("products", encodedValue);
        cookie.setMaxAge(7 * 24 * 60 * 60);
        cookie.setPath("/");
        response.addCookie(cookie);
        return "redirect:/product/list";
    }


    private List<Product> parseProductsFromCookie(String cookieValue) {
        List<Product> productList = new ArrayList<>();
        if (!cookieValue.isEmpty()) {
            try {
                String decoded = URLDecoder.decode(cookieValue, StandardCharsets.UTF_8);
                String cleaned = decoded.replaceAll("^\\[|]$", "");
                String[] items = cleaned.split(",");
                for (String item : items) {
                    String[] parts = item.split("\\|");
                    if (parts.length == 3) {
                        int id = Integer.parseInt(parts[0].trim());
                        String name = parts[1].trim();
                        double price = Double.parseDouble(parts[2].trim());
                        productList.add(new Product(id, name, price));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return productList;
    }

    private List<Cart> parseCartsFromCookie(String cookieValue) {
        List<Cart> cartList = new ArrayList<>();
        if (!cookieValue.isEmpty()) {
            try {
                String decoded = URLDecoder.decode(cookieValue, StandardCharsets.UTF_8);
                String cleaned = decoded.replaceAll("^\\[|]$", "");
                String[] items = cleaned.split(",");
                for (String item : items) {
                    String[] parts = item.split("\\|");
                    if (parts.length == 3) {
                        int id = Integer.parseInt(parts[0].trim());
                        int productId = Integer.parseInt(parts[1].trim());
                        int quantity = Integer.parseInt(parts[2].trim());
                        cartList.add(new Cart(id, productId, quantity));
                    }
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return cartList;
    }

    private String buildCookieFromProducts(List<Product> productList) {
        StringBuilder cookieValue = new StringBuilder("[");
        for (int i = 0; i < productList.size(); i++) {
            Product p = productList.get(i);
            cookieValue.append(p.getId()).append("|").append(p.getName()).append("|").append(p.getPrice());
            if (i < productList.size() - 1) {
                cookieValue.append(",");
            }
        }
        cookieValue.append("]");
        return cookieValue.toString();
    }

    private String buildCookieFromCarts(List<Cart> cartList) {
        StringBuilder cookieValue = new StringBuilder("[");
        for (int i = 0; i < cartList.size(); i++) {
            Cart c = cartList.get(i);
            cookieValue.append(c.getId()).append("|").append(c.getProductId()).append("|").append(c.getQuantity());
            if (i < cartList.size() - 1) {
                cookieValue.append(",");
            }
        }
        cookieValue.append("]");
        return cookieValue.toString();
    }
}
