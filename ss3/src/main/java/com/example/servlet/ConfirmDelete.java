package com.example.servlet;

import com.example.service.UserManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "ConfirmDelete", value = "/delete-user")
public class ConfirmDelete extends HttpServlet{
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
        req.getRequestDispatcher("confirmDelete.jsp").forward(req,res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException{
        int id = Integer.parseInt(req.getParameter("id"));
        UserManager.removeUser(id);
        res.sendRedirect("list-user");
    }

}
