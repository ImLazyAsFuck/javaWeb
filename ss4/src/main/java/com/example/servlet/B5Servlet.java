package com.example.servlet;

import com.example.model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/b5")
public class B5Servlet extends HttpServlet{
    private static final List<Product> products = new ArrayList<>();

    public void init(){
        products.add(new Product(1, "Điện thoại", 1000, "Điện thoại Apple"));
        products.add(new Product(2, "Laptop", 2000, "Laptop Apple"));
        products.add(new Product(3, "Máy tính", 3000, "Máy tính Apple"));
    }

    public void doGet( HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        req.setAttribute("products", products);
        req.getRequestDispatcher("b5.jsp").forward(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        String name = req.getParameter("name");

        List<Product> searchResults = new ArrayList<>();
        
        if (name != null && !name.trim().isEmpty()) {
            String searchTerm = name.toLowerCase().trim();
            
            for (Product product : products) {
                if (product.getName().toLowerCase().contains(searchTerm)) {
                    searchResults.add(product);
                }
            }
        } else {
            searchResults.addAll(products);
        }
        req.setAttribute("products", searchResults);
        req.getRequestDispatcher("b5.jsp").forward(req, res);
    }
}
