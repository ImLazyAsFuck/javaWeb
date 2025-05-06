package com.example.serlvet;

import com.example.model.Product;
import com.example.service.ProductFile;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

import static com.example.service.ProductFile.productList;

@WebServlet(name = "Products", value = "/products")
public class AddProduct extends HttpServlet{

    public void init(){
        ProductFile.loadProductsFromFile();
        if(productList == null){
            productList = new ArrayList<>();
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        String action = request.getParameter("action");
        String idParam = request.getParameter("id");
        
        if (idParam != null && "edit".equals(action)) {
            try {
                int id = Integer.parseInt(idParam);
                response.sendRedirect("editproduct?id=" + id);
                return;
            } catch (NumberFormatException e) {
                System.err.println("Invalid ID format: " + e.getMessage());
            }
        }
        
        System.out.println("Product list size: " + (productList != null? productList.size() : "null"));
        request.setAttribute("products", productList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("products.jsp");
        dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException{
        try{
            String name = request.getParameter("name");
            String priceStr = request.getParameter("price");
            
            System.out.println("Received product data - Name: " + name + ", Price: " + priceStr);
            
            if(name == null || priceStr == null) {
                System.err.println("Name or price parameter is missing");
                response.sendRedirect("products");
                return;
            }
            
            double price = Double.parseDouble(priceStr);

            if(!name.trim().isEmpty() && price > 0){
                Product newProduct = new Product(name, price);
                if(productList == null){
                    productList = new ArrayList<>();
                }
                productList.add(newProduct);
                System.out.println("Added new product: " + newProduct.getName() + " with ID: " + newProduct.getId());
                ProductFile.saveProductsToFile();
                System.out.println("Current product list size: " + productList.size());
            } else {
                System.err.println("Invalid product data: name empty or price <= 0");
            }
        }catch(NumberFormatException e){
            System.err.println("Invalid price format: " + e.getMessage());
        }catch(Exception e){
            System.err.println("Error adding product: " + e.getMessage());
            e.printStackTrace();
        }
        response.sendRedirect("products");
    }

}