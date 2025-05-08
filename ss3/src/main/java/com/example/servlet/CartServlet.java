package com.example.servlet;

import com.example.model.Cart;
import com.example.service.CartManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "CartServlet", value = "/cart")
public class CartServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        List<Cart> cartItems = CartManager.getCart();
        req.setAttribute("cartItems", cartItems);
        req.setAttribute("totalPrice", CartManager.getTotalPrice());
        req.getRequestDispatcher("cart.jsp").forward(req, res);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String action = req.getParameter("action");
        
        if ("remove".equals(action)) {
            int productId = Integer.parseInt(req.getParameter("productId"));
            CartManager.removeProduct(productId);
            req.getSession().setAttribute("message", "Product removed from cart!");
        } else if ("update".equals(action)) {
            int productId = Integer.parseInt(req.getParameter("productId"));
            int quantity = Integer.parseInt(req.getParameter("quantity"));
            CartManager.updateQuantity(productId, quantity);
            req.getSession().setAttribute("message", "Cart updated successfully!");
        } else if ("clear".equals(action)) {
            CartManager.clearCart();
            req.getSession().setAttribute("message", "Cart cleared!");
        }
        
        res.sendRedirect("cart");
    }
}
