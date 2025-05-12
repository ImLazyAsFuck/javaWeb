package com.example.ss5.controller;

import com.example.ss5.model.Product;
import com.example.ss5.service.productservice.ProductService;
import com.example.ss5.service.productservice.ProductServiceImp;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/b1")
public class ProductController extends HttpServlet{
    private final ProductService PRODUCT_SERVICE = new ProductServiceImp();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        String action = req.getParameter("action");
        if(action != null && action.equals("findAll")){
            findAll(req, res);
        }else{
            res.sendRedirect("views/error.jsp");
        }
    }

    public void findAll(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        List<Product> products = PRODUCT_SERVICE.findAll();
        System.out.println(products);
        req.setAttribute("products", products);
        req.getRequestDispatcher("views/product.jsp").forward(req, res);
    }
}