package com.example.ss6.controller;

import com.example.ss6.model.ProductCart;
import com.example.ss6.service.productcartservice.ProductCartService;
import com.example.ss6.service.productcartservice.ProductCartServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/cart")
public class CartController extends HttpServlet {
    private final ProductCartService CART_SERVICE = new ProductCartServiceImpl();
    
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        String sessionId = session.getId();
        
        List<ProductCart> cartItems = CART_SERVICE.findBySessionId(sessionId);
        double cartTotal = CART_SERVICE.getCartTotal(sessionId);
        
        req.setAttribute("cartItems", cartItems);
        req.setAttribute("cartTotal", cartTotal);
        
        req.getRequestDispatcher("/views/cart/cart.jsp").forward(req, res);
    }
    
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String action = req.getParameter("action");
        
        if ("remove".equals(action)) {
            removeFromCart(req, res);
        } else {
            doGet(req, res);
        }
    }
    
    private void removeFromCart(HttpServletRequest req, HttpServletResponse res) throws IOException {
        int cartItemId = Integer.parseInt(req.getParameter("cartItemId"));
        
        CART_SERVICE.removeFromCart(cartItemId);
        
        res.sendRedirect("/cart");
    }
}
