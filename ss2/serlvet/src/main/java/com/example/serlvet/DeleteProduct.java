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
import java.util.Iterator;

import static com.example.service.ProductFile.productList;

@WebServlet(name = "DeleteProduct", value = "/delete-product")
public class DeleteProduct extends HttpServlet {

    public void init(){
        if (productList == null) {
            ProductFile.loadProductsFromFile();
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        
        if (idParam == null || idParam.isEmpty()) {
            response.sendRedirect("products");
            return;
        }
        
        try {
            int id = Integer.parseInt(idParam);
            Product productToDelete = null;
            for (Product p : productList) {
                if (p.getId() == id) {
                    productToDelete = p;
                    break;
                }
            }
            
            if (productToDelete != null) {
                request.setAttribute("product", productToDelete);
                RequestDispatcher dispatcher = request.getRequestDispatcher("deleteConfirm.jsp");
                dispatcher.forward(request, response);
            } else {
                System.err.println("Product not found with ID: " + id);
                response.sendRedirect("products");
            }
            
        } catch (NumberFormatException e) {
            System.err.println("Invalid product ID format: " + e.getMessage());
            response.sendRedirect("products");
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idParam = request.getParameter("id");
        String confirmed = request.getParameter("confirmed");

        if ("yes".equals(confirmed) && idParam != null && !idParam.isEmpty()) {
            try {
                int id = Integer.parseInt(idParam);

                boolean deleted = deleteProduct(id);
                
                if (deleted) {
                    ProductFile.saveProductsToFile();
                    System.out.println("Product with ID " + id + " was deleted successfully");
                } else {
                    System.err.println("Product with ID " + id + " was not found for deletion");
                }
                
            } catch (NumberFormatException e) {
                System.err.println("Invalid product ID format: " + e.getMessage());
            }
        }

        response.sendRedirect("products");
    }
    
    private boolean deleteProduct(int id) {
        if (productList != null) {
            Iterator<Product> iterator = productList.iterator();
            while (iterator.hasNext()) {
                Product product = iterator.next();
                if (product.getId() == id) {
                    iterator.remove();
                    return true;
                }
            }
        }
        return false;
    }
}
