package com.example.serlvet;

import java.io.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "UserRegistrationSerlvet", value = "/user-registration-servlet")
public class UserRegistrationSerlvet extends HttpServlet{

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.sendRedirect("register2");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        request.setAttribute("name", name);
        request.setAttribute("email", email);
        request.setAttribute("password", password);
        RequestDispatcher dispatcher = request.getRequestDispatcher("userInfo.jsp");
        dispatcher.forward(request, response);
    }

    public void destroy(){
    }
}