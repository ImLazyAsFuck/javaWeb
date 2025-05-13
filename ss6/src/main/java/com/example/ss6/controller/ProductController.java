package com.example.ss6.controller;

import com.example.ss6.model.Product;
import com.example.ss6.service.productcartservice.ProductCartService;
import com.example.ss6.service.productcartservice.ProductCartServiceImpl;
import com.example.ss6.service.productservice.ProductService;
import com.example.ss6.service.productservice.ProductServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/products")
public class ProductController extends HttpServlet {
    private final ProductService PRODUCT_SERVICE = new ProductServiceImpl();
    private final ProductCartService CART_SERVICE = new ProductCartServiceImpl();
    
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        List<Product> products = PRODUCT_SERVICE.findAll();
        req.setAttribute("products", products);
        req.getRequestDispatcher("/views/product/listProduct.jsp").forward(req, res);
    }
    
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String action = req.getParameter("action");
        
        if ("addToCart".equals(action)) {
            addToCart(req, res);
        } else {
            doGet(req, res);
        }
    }
    
    private void addToCart(HttpServletRequest req, HttpServletResponse res) throws IOException {
        HttpSession session = req.getSession(true);
        String sessionId = session.getId();
        
        int productId = Integer.parseInt(req.getParameter("productId"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        
        CART_SERVICE.addToCart(sessionId, productId, quantity);
        
        res.sendRedirect("/products");
    }
}
