package com.example.ss6.controller;

import com.example.ss6.model.User;
import com.example.ss6.service.userservice.UserService;
import com.example.ss6.service.userservice.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private final UserService USER_SERVICE = new UserServiceImpl();
    
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            res.sendRedirect("/books");
            return;
        }
        req.getRequestDispatcher("/views/auth/login.jsp").forward(req, res);
    }
    
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        
        if (USER_SERVICE.authenticate(username, password)) {
            User user = USER_SERVICE.findByUsername(username);
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            res.sendRedirect("/books");
        } else {
            req.setAttribute("errorMessage", "Invalid username or password");
            req.getRequestDispatcher("/views/auth/login.jsp").forward(req, res);
        }
    }
}
