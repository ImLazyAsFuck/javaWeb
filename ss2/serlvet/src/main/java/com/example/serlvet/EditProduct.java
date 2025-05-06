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

import static com.example.service.ProductFile.productList;

@WebServlet(name = "EditProduct", value = "/editproduct")
public class EditProduct extends HttpServlet {

    public void init(){
        ProductFile.loadProductsFromFile();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        
        if (idParam != null && !idParam.isEmpty()) {
            try {
                int id = Integer.parseInt(idParam);

                Product productToEdit = null;
                for (Product p : productList) {
                    if (p.getId() == id) {
                        productToEdit = p;
                        break;
                    }
                }
                
                if (productToEdit != null) {
                    request.setAttribute("product", productToEdit);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("editProduct.jsp");
                    dispatcher.forward(request, response);
                    return;
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid product ID format: " + e.getMessage());
            }
        }

        response.sendRedirect("products");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idParam = request.getParameter("id");
        String name = request.getParameter("name");
        String priceParam = request.getParameter("price");
        
        if (idParam != null && !idParam.isEmpty() && name != null && !name.trim().isEmpty() && priceParam != null && !priceParam.isEmpty()) {
            try {
                int id = Integer.parseInt(idParam);
                double price = Double.parseDouble(priceParam);
                
                if (price <= 0) {
                    System.err.println("Invalid price: must be greater than 0");
                    response.sendRedirect("products");
                    return;
                }
                

                boolean updated = false;
                for (Product p : productList) {
                    if (p.getId() == id) {
                        p.setName(name);
                        p.setPrice(price);
                        updated = true;
                        break;
                    }
                }
                
                if (updated) {

                    ProductFile.saveProductsToFile();
                    System.out.println("Product updated successfully: ID=" + id);
                } else {
                    System.err.println("Product not found with ID: " + id);
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid number format: " + e.getMessage());
            }
        } else {
            System.err.println("Missing required parameters for product update");
        }
        

        response.sendRedirect("products");
    }

}