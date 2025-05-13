package com.example.ss6.controller;

import com.example.ss6.model.User;
import com.example.ss6.service.userservice.UserService;
import com.example.ss6.service.userservice.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/register")
public class RegisterController extends HttpServlet {
    private final UserService USER_SERVICE = new UserServiceImpl();
    
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.getRequestDispatcher("/views/auth/register.jsp").forward(req, res);
    }
    
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setPhone(phone);
        
        if (USER_SERVICE.findByUsername(username) != null) {
            req.setAttribute("errorMessage", "Username already exists");
            req.getRequestDispatcher("/views/auth/register.jsp").forward(req, res);
            return;
        }
        
        if (USER_SERVICE.findByEmail(email) != null) {
            req.setAttribute("errorMessage", "Email already exists");
            req.getRequestDispatcher("/views/auth/register.jsp").forward(req, res);
            return;
        }
        
        if (USER_SERVICE.save(user)) {
            req.setAttribute("successMessage", "Registration successful. Please login.");
            req.getRequestDispatcher("/views/auth/register.jsp").forward(req, res);
        } else {
            req.setAttribute("errorMessage", "Registration failed. Please try again.");
            req.getRequestDispatcher("/views/auth/register.jsp").forward(req, res);
        }
    }
}
