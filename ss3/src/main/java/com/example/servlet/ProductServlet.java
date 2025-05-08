package com.example.servlet;

import com.example.model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import com.example.service.CartManager;


@WebServlet(name = "Product", value = "/product")
public class ProductServlet extends HttpServlet {
    private static List<Product> productList;

    @Override
    public void init() throws ServletException {
        Product.resetCounter();
        productList = Arrays.asList(
                new Product("Iphone X", 2000.0),
                new Product("Vivo Book", 1000.0),
                new Product("Check", 2000.0)
        );
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        req.setAttribute("products", productList);
        req.getRequestDispatcher("product.jsp").forward(req, res);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        try {
            String productIdStr = req.getParameter("productId");
    
                if (productIdStr != null) {
                    int productId = Integer.parseInt(productIdStr);
    
                    Product selectedProduct = productList.stream()
                            .filter(p -> p.getId() == productId)
                            .findFirst()
                            .orElse(null);
    
                    if (selectedProduct != null) {
                        CartManager.addProduct(selectedProduct);
                        req.getSession().setAttribute("message", "Product added to cart successfully!");
                    } else {
                        req.getSession().setAttribute("error", "Invalid product!");
                }
            } else {
                req.getSession().setAttribute("error", "Missing product information!");
            }
        } catch (NumberFormatException e) {
            req.getSession().setAttribute("error", "Invalid product information!");
        }
        res.sendRedirect("product");
    }
}
