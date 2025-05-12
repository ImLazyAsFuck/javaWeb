package com.example.ss5.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/taskList")
public class TaskListController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        
        List<String> tasks = new ArrayList<>();
        tasks.add("Complete Java assignment");
        tasks.add("Study Servlets and JSP");
        tasks.add("Practice database integration");
        tasks.add("Work on front-end designs");
        tasks.add("Review week's materials");
        
        request.setAttribute("tasks", tasks);
        request.getRequestDispatcher("/views/taskList.jsp").forward(request, response);
    }
}
