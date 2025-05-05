package com.mystorage.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/todo")
public class ToDoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ArrayList<String> tasks = new ArrayList<>();
    private ArrayList<String> statuses = new ArrayList<>();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String newTask = request.getParameter("newJob");
        if (newTask != null && !newTask.isEmpty()) {
            tasks.add(newTask);
            statuses.add("Doing");
        }
        request.setAttribute("tasks", tasks);
        request.setAttribute("statuses", statuses);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/todo.jsp");
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String taskId = request.getParameter("taskId");
        if (taskId != null && !taskId.isEmpty()) {
            int index = Integer.parseInt(taskId);
            if (index >= 0 && index < statuses.size()) {
                statuses.set(index, "Completed");
            }
        }
        request.setAttribute("tasks", tasks);
        request.setAttribute("statuses", statuses);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/todo.jsp");
        dispatcher.forward(request, response);
    }
}