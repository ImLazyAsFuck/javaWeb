package com.example.ss5.controller;

import com.example.ss5.model.Task;
import com.example.ss5.service.taskservice.TaskService;
import com.example.ss5.service.taskservice.TaskServiceImp;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/tasks")
public class TaskController extends HttpServlet{
    private static int idSequence = 0;
    private final TaskService TASK_SERVICE = new TaskServiceImp();

    @Override
    public void init() throws ServletException {
        super.init();
        initializeIdSequence();
    }
    
    private void initializeIdSequence() {
        List<Task> tasks = TASK_SERVICE.findAll();
        if (tasks != null && !tasks.isEmpty()) {
            int highestId = 0;
            for (Task task : tasks) {
                if (task.getId() > highestId) {
                    highestId = task.getId();
                }
            }
            idSequence = highestId + 1;
        } else {
            idSequence = 1;
        }
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
        String action = req.getParameter("action");
        if(action == null || action.isEmpty()){
            listTasks(req, res);
            return;
        }
        switch(action){
            case "add":
                showAddForm(req, res);
                break;
            case "edit":
                showEditForm(req, res);
                break;
            default:
                listTasks(req, res);
                break;
        }
    }

    public void listTasks(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        List<Task> tasks = TASK_SERVICE.findAll();
        req.setAttribute("tasks", tasks);
        req.getRequestDispatcher("views/taskList.jsp").forward(req, res);
    }

    public void showAddForm(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        req.getRequestDispatcher("views/taskForm.jsp").forward(req, res);
    }
    
    public void showEditForm(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            List<Task> tasks = TASK_SERVICE.findAll();
            Task taskToEdit = null;
            
            for (Task task : tasks) {
                if (task.getId() == id) {
                    taskToEdit = task;
                    break;
                }
            }
            
            if (taskToEdit != null) {
                req.setAttribute("task", taskToEdit);
                req.getRequestDispatcher("views/taskEditForm.jsp").forward(req, res);
            } else {
                res.sendRedirect("/tasks");
            }
        } catch (NumberFormatException e) {
            res.sendRedirect("/tasks");
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        String action = req.getParameter("action");
        if (action == null) {
            res.sendRedirect("/tasks");
            return;
        }

        switch (action) {
            case "delete":
                deleteTask(req, res);
                break;
            case "toggleStatus":
                toggleTaskStatus(req, res);
                break;
            case "create":
                createTask(req, res);
                break;
            case "update":
                updateTask(req, res);
                break;
            default:
                res.sendRedirect("/tasks");
                break;
        }
    }

    private void toggleTaskStatus(HttpServletRequest req, HttpServletResponse res) throws IOException {
        try {
            int taskId = Integer.parseInt(req.getParameter("taskId"));
            List<Task> tasks = TASK_SERVICE.findAll();
            Task existingTask = null;
            for (Task task : tasks) {
                if (task.getId() == taskId) {
                    existingTask = task;
                    break;
                }
            }
            
            if (existingTask != null) {
                Task task = new Task();
                task.setId(taskId);
                task.setName(existingTask.getName());
                task.setDueDate(existingTask.getDueDate());
                task.setCompleted(true);
                
                TASK_SERVICE.update(task);
            }
            
            res.sendRedirect("/tasks");
        } catch (NumberFormatException e) {
            res.sendRedirect("/tasks");
        }
    }

    private void deleteTask(HttpServletRequest req, HttpServletResponse res) throws IOException {
        try {
            int taskId = Integer.parseInt(req.getParameter("taskId"));
            TASK_SERVICE.delete(taskId);
            res.sendRedirect("/tasks");
        } catch (NumberFormatException e) {
            res.sendRedirect("/tasks");
        }
    }
    
    private void createTask(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String name = req.getParameter("name");
        if (name != null && !name.trim().isEmpty()) {
            Task task = new Task();
            task.setId(idSequence++);
            task.setName(name);
            task.setCompleted(false);
            TASK_SERVICE.save(task);
        }
        res.sendRedirect("/tasks");
    }
    
    private void updateTask(HttpServletRequest req, HttpServletResponse res) throws IOException {
        try {
            int taskId = Integer.parseInt(req.getParameter("taskId"));
            String name = req.getParameter("name");
            boolean isAlreadyCompleted = "true".equals(req.getParameter("isAlreadyCompleted"));
            boolean completed = "on".equals(req.getParameter("completed"));
            if (isAlreadyCompleted) {
                completed = true;
            }
            
            if (name != null && !name.trim().isEmpty()) {
                Task task = new Task();
                task.setId(taskId);
                task.setName(name);
                task.setCompleted(completed);
                
                TASK_SERVICE.update(task);
            }
            res.sendRedirect("/tasks");
        } catch (NumberFormatException e) {
            res.sendRedirect("/tasks");
        }
    }
}
