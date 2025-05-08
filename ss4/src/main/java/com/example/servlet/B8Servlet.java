package com.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet("/b8")
public class B8Servlet extends HttpServlet{
    private final Map<String, Double> revenues = Map.of(
            "Tháng 1", 1000.0,
            "Tháng 2", 2000.0,
            "Tháng 3", 3000.0
    );

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        req.setAttribute("revenues", revenues);
        req.getRequestDispatcher("b8.jsp").forward(req, res);
    }
}
