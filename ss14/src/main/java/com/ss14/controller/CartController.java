package com.ss14.controller;

import com.ss14.dto.CartDTO;
import com.ss14.model.Cart;
import com.ss14.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController{
    @GetMapping("/list")
    public String getCartList(@CookieValue(value = "carts", defaultValue = "") String carts,
                              @CookieValue(value = "products", defaultValue = "") String products,
                              Model model){
        List<Cart> cartList = parseCartsFromCookie(carts);
        List<Product> productList = parseProductsFromCookie(products);

        List<CartDTO> resultList = new ArrayList<>();

        for (Cart c : cartList) {
            for (Product p : productList) {
                if (p.getId() == c.getProductId()) {
                    resultList.add(new CartDTO(c.getId(), p, c.getQuantity()));
                    break;
                }
            }
        }
        model.addAttribute("carts", resultList);
        return "b2_4/carts";
    }

    @GetMapping("/delete/{id}")
    public String deleteCart(@PathVariable int id, @CookieValue(value = "carts", defaultValue = "") String carts, Model model, HttpServletResponse response){
        List<Cart> cartList = parseCartsFromCookie(carts);
        for (int i = 0; i < cartList.size(); i++) {
            Cart c = cartList.get(i);
            if (c.getId() == id) {
                cartList.remove(i);
                break;
            }
        }
        String cookieValue = buildCookieFromCarts(cartList);
        String encodedValue = URLEncoder.encode(cookieValue, StandardCharsets.UTF_8);
        Cookie cookie = new Cookie("carts", encodedValue);
        cookie.setMaxAge(7 * 24 * 60 * 60);
        cookie.setPath("/");
        response.addCookie(cookie);
        model.addAttribute("carts", cartList);
        return "redirect:/cart/list";
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
