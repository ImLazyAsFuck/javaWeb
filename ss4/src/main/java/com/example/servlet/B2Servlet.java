package com.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/b2")
public class B2Servlet extends HttpServlet{
    private String username;
    private String password;

    public void init(){
        username = "admin";
        password = "123456";
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        req.setAttribute("username", username);
        req.setAttribute("password", password);
        req.getRequestDispatcher("b2.jsp").forward(req, res);
    }
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if(username.equals(this.username) && password.equals(this.password)){
            req.setAttribute("title", "Login successfully");
            req.setAttribute("success", "Hello, " + this.username);
        } else {
            req.setAttribute("title", "Login failed");
            req.setAttribute("error", "Username or password is incorrect");
        }
        req.getRequestDispatcher("b2.jsp").forward(req, res);
    }
}
