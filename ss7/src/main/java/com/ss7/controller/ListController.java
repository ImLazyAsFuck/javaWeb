package com.ss7.controller;

import com.ss7.model.CartItem;
import com.ss7.model.Product;
import com.ss7.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ListController {


    private List<Product> getProductList() {
        List<Product> products = new ArrayList<>();
        
        products.add(new Product(1, "Laptop Dell XPS 13", 1299.99, 10,
                "High-performance laptop with 13-inch display, Intel Core i7, 16GB RAM, 512GB SSD",
                "https://images.unsplash.com/photo-1593642632823-8f785ba67e45"));
                
        products.add(new Product(2, "iPhone 15 Pro", 999.99, 15,
                "Latest iPhone with A16 Bionic chip, 6.1-inch Super Retina XDR display, 128GB storage",
                "https://images.unsplash.com/photo-1691293441848-b764cf05d952"));
                
        products.add(new Product(3, "Samsung QLED 4K TV", 1499.99, 5,
                "55-inch QLED 4K Smart TV with Quantum HDR, Alexa compatibility, and Smart Hub",
                "https://images.unsplash.com/photo-1593305841991-05c297ba4575"));
                
        products.add(new Product(4, "Sony WH-1000XM5", 349.99, 20,
                "Wireless noise-cancelling headphones with up to 30 hours of battery life and exceptional sound quality",
                "https://images.unsplash.com/photo-1546435770-a3e426bf472b"));
                
        products.add(new Product(5, "Canon EOS R6", 2499.99, 7,
                "Full-frame mirrorless camera with 20.1MP CMOS sensor, 4K video recording, and dual pixel CMOS AF",
                "https://images.unsplash.com/photo-1516035069371-29a1b244cc32"));
                
        return products;
    }
    

    private Product findProductById(int id) {
        List<Product> products = getProductList();
        for (Product p : products) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }
    

    @SuppressWarnings("unchecked")
    private List<CartItem> getCartItems(HttpSession session) {
        List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cartItems");
        if (cartItems == null) {
            cartItems = new ArrayList<>();
            session.setAttribute("cartItems", cartItems);
        }
        return cartItems;
    }
    

    private double calculateCartTotal(List<CartItem> cartItems) {
        double total = 0;
        for (CartItem item : cartItems) {
            total += item.getSubtotal();
        }
        return total;
    }
    

    private int calculateCartSize(List<CartItem> cartItems) {
        int size = 0;
        for (CartItem item : cartItems) {
            size += item.getQuantity();
        }
        return size;
    }


    @GetMapping("/list")
    public String showProductList(Model model, HttpSession session) {
        List<Product> products = getProductList();
        model.addAttribute("products", products);
        
        // Get cart size for the badge
        List<CartItem> cartItems = getCartItems(session);
        model.addAttribute("cartSize", calculateCartSize(cartItems));
        
        return "product_list";
    }


    @GetMapping("/details")
    public String showProductDetails(@RequestParam("id") int id, Model model, HttpSession session) {
        Product product = findProductById(id);
        
        if (product != null) {
            model.addAttribute("product", product);
            
            // Get cart size for the badge
            List<CartItem> cartItems = getCartItems(session);
            model.addAttribute("cartSize", calculateCartSize(cartItems));
            
            return "product_details";
        } else {
            return "redirect:/products/list";
        }
    }
    

    @PostMapping("/add-to-cart")
    public String addToCart(
            @RequestParam("productId") int productId,
            @RequestParam("quantity") int quantity,
            HttpSession session,
            RedirectAttributes redirectAttributes) {
        

        if (quantity <= 0) {
            redirectAttributes.addFlashAttribute("errorMessage", "Quantity must be greater than 0");
            return "redirect:/products/list";
        }
        

        Product product = findProductById(productId);
        if (product == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Product not found");
            return "redirect:/products/list";
        }
        

        if (quantity > product.getStock()) {
            redirectAttributes.addFlashAttribute("errorMessage", 
                    "Cannot add " + quantity + " items. Only " + product.getStock() + " available in stock.");
            return "redirect:/products/list";
        }
        

        List<CartItem> cartItems = getCartItems(session);
        

        boolean productExists = false;
        for (CartItem item : cartItems) {
            if (item.getProduct().getId() == productId) {

                int newQuantity = item.getQuantity() + quantity;
                if (newQuantity > product.getStock()) {
                    redirectAttributes.addFlashAttribute("errorMessage", 
                            "Cannot add " + quantity + " more items. It would exceed available stock.");
                    return "redirect:/products/list";
                }

                item.setQuantity(newQuantity);
                productExists = true;
                break;
            }
        }

        if (!productExists) {
            cartItems.add(new CartItem(product, quantity));
        }
        

        session.setAttribute("cartItems", cartItems);

        redirectAttributes.addFlashAttribute("successMessage", 
                quantity + " " + product.getName() + " added to cart");

        String referer = "/products/list";
        return "redirect:" + referer;
    }
    

    @GetMapping("/cart")
    public String showCart(Model model, HttpSession session) {
        List<CartItem> cartItems = getCartItems(session);
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("cartTotal", calculateCartTotal(cartItems));
        model.addAttribute("cartSize", calculateCartSize(cartItems));
        return "cart";
    }
    

    @PostMapping("/increase-quantity")
    public String increaseQuantity(
            @RequestParam("productId") int productId,
            HttpSession session,
            RedirectAttributes redirectAttributes) {
        
        List<CartItem> cartItems = getCartItems(session);
        
        for (CartItem item : cartItems) {
            if (item.getProduct().getId() == productId) {

                if (item.getQuantity() < item.getProduct().getStock()) {
                    item.setQuantity(item.getQuantity() + 1);
                } else {
                    redirectAttributes.addFlashAttribute("errorMessage", 
                            "Cannot increase quantity. Maximum stock reached.");
                }
                break;
            }
        }
        
        session.setAttribute("cartItems", cartItems);
        return "redirect:/products/cart";
    }
    

    @PostMapping("/decrease-quantity")
    public String decreaseQuantity(
            @RequestParam("productId") int productId,
            HttpSession session) {
        
        List<CartItem> cartItems = getCartItems(session);
        
        for (int i = 0; i < cartItems.size(); i++) {
            CartItem item = cartItems.get(i);
            if (item.getProduct().getId() == productId) {
                if (item.getQuantity() > 1) {
                    item.setQuantity(item.getQuantity() - 1);
                } else {
                    cartItems.remove(i);
                }
                break;
            }
        }
        
        session.setAttribute("cartItems", cartItems);
        return "redirect:/products/cart";
    }
    

    @PostMapping("/remove-from-cart")
    public String removeFromCart(
            @RequestParam("productId") int productId,
            HttpSession session) {
        
        List<CartItem> cartItems = getCartItems(session);
        
        for (int i = 0; i < cartItems.size(); i++) {
            if (cartItems.get(i).getProduct().getId() == productId) {
                cartItems.remove(i);
                break;
            }
        }
        
        session.setAttribute("cartItems", cartItems);
        return "redirect:/products/cart";
    }
}
