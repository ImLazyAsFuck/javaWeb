package com.example.servlet;
import java.io.*;

import com.example.model.User;
import com.example.service.UserManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "ListUser", value = "/list-user")
public class ListUser extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
        req.setAttribute("users", UserManager.getUsers());
        req.getRequestDispatcher("listUser.jsp").forward(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        User user = new User(name, email);
        UserManager.addUser(user);
        res.sendRedirect("list-user");
    }

    public void destroy() {

    }
}