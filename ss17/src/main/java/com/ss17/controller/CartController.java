package com.ss17.controller;

import com.ss17.entity.Customer;
import com.ss17.entity.ProductCart;
import com.ss17.model.CartItemView;
import com.ss17.service.product.ProductService;
import com.ss17.service.productcart.ProductCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController{
    @Autowired
    private ProductCartService productCartService;
    @Autowired
    private ProductService productService;

    @Autowired
    private HttpSession session;

    @GetMapping
    public String cart(Model model){
        if(session.getAttribute("customer") == null){
            return "redirect:/login";
        }
        Customer customer = (Customer) session.getAttribute("customer");
        List<ProductCart> productCarts = productCartService.findByCustomerId(customer.getId());
        List<CartItemView> cartItems = new ArrayList<>();
        for(ProductCart productCart : productCarts){
            CartItemView cartItem = new CartItemView();
            cartItem.setProduct(productService.findById(productCart.getProductId()));
            cartItem.setCart(productCart);
            cartItems.add(cartItem);
        }
        model.addAttribute("cartItems", cartItems);
        return "user/cart";
    }

    @PostMapping
    public String updateQuantity(@RequestParam int id, @RequestParam int quantity){
        if(session.getAttribute("customer") == null){
            return "redirect:/login";
        }
        ProductCart productCart = productCartService.findById(id);
        if(productCart == null){
            return "redirect:/user/home";
        }
        productCart.setQuantity(quantity);
        productCartService.update(productCart);
        return "redirect:/user/cart";
    }

    @GetMapping("/delete/{id}")
    public String removeItem(@RequestParam int id){
        if(session.getAttribute("customer") == null){
            return "redirect:/login";
        }
        ProductCart productCart = productCartService.findById(id);
        productCartService.delete(productCart);
        return "redirect:/user/cart";
    }

}
