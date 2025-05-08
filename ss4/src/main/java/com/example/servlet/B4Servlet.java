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

@WebServlet("/b4")
public class B4Servlet extends HttpServlet{
    private static final List<Product> products = new ArrayList<>();

    public void init(){
        products.add(new Product(1, "", 1000, "Điện thoại Apple"));
        products.add(new Product(2, "Laptop", 2000, ""));
        products.add(new Product(3, "Máy tính", 0, "Máy tính Apple"));
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        req.setAttribute("products", products);
        req.getRequestDispatcher("b4.jsp").forward(req, res);
    }
}
