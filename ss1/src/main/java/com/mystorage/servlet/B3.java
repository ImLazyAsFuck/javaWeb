package com.mystorage.servlet;

import java.io.*;
import com.mystorage.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "b3", value = "/b3")
public class B3 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        System.out.println("Servlet hit! Forwarding to userInfo.jsp...");
        User user = new User("Nguyễn Văn A", 25);
        request.setAttribute("user", user);
        request.getRequestDispatcher("/userInfo.jsp").forward(request, response);
    }
}