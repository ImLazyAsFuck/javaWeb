package com.example.category.controller;

import com.example.category.model.category.CatalogStatus;
import com.example.category.model.category.Category;
import com.example.category.service.categoryservice.CategoryService;
import com.example.category.service.categoryservice.CategoryServiceImp;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/category-controller")
public class CategoryController extends HttpServlet{
    private final CategoryService CATEGORY_SERVICE = new CategoryServiceImp();

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        String action = req.getParameter("action");
        if(action.equals("findAll")){
            try{
                findAll(req, res);
            }catch(SQLException e){
                throw new RuntimeException(e);
            }
        }
        if (action.equals("edit")) {
            try {
                editForm(req, res);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (action.equals("confirmDelete")) {
            try {
                deleteCategory(req, res);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void findAll(HttpServletRequest req, HttpServletResponse res) throws SQLException, ServletException, IOException{
        List<Category> categories = CATEGORY_SERVICE.findAll();
        req.setAttribute("categories", categories);
        req.getRequestDispatcher("views/category.jsp").forward(req, res);
    }
    private void deleteCategory(HttpServletRequest req, HttpServletResponse res) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        req.setAttribute("id", id);
        req.getRequestDispatcher("views/confirmDelete.jsp").forward(req, res);
    }

    public void editForm(HttpServletRequest req, HttpServletResponse res) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Category category = CATEGORY_SERVICE.findById(id);
        System.out.println(category);
        req.setAttribute("category", category);
        req.getRequestDispatcher("views/editCategory.jsp").forward(req, res);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action.equals("add")) {
            Category catalog = new Category();
            catalog.setName(request.getParameter("catalogName"));
            catalog.setDescription(request.getParameter("description"));
            catalog.setStatus(CatalogStatus.ACTIVE);
            boolean result = CATEGORY_SERVICE.save(catalog);
            if (result) {
                try{
                    findAll(request, response);
                }catch(SQLException e){
                    throw new RuntimeException(e);
                }
            } else {
                request.getRequestDispatcher("views/error.jsp").forward(request, response);
            }
        }
        if (action.equals("update")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Category catalog = new Category();
            catalog.setId(id);
            catalog.setName(request.getParameter("catalogName"));
            catalog.setDescription(request.getParameter("description"));
            catalog.setStatus(CatalogStatus.valueOf(request.getParameter("status")));

            boolean result = CATEGORY_SERVICE.update(catalog);
            if (result) {
                try {
                    findAll(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else {
                request.getRequestDispatcher("views/error.jsp").forward(request, response);
            }
        }
        if (action.equals("delete")) {
            int id = Integer.parseInt(request.getParameter("id"));
            boolean result = CATEGORY_SERVICE.delete(id);

            if (result) {
                try {
                    findAll(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else {
                request.getRequestDispatcher("views/error.jsp").forward(request, response);
            }
        }
    }
}
