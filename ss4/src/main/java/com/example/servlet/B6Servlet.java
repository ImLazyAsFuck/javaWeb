package com.example.servlet;

import com.example.model.Student;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;

@WebServlet("/b6")
public class B6Servlet extends HttpServlet{
    private List<Student> students = new ArrayList<>();

    public void init(){
        students.add(new Student(1, "Lam", 18, 3.8));
        students.add(new Student(2, "Minh", 19, 3.9));
        students.add(new Student(3, "Nguyen", 20, 7.1));
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws jakarta.servlet.ServletException, java.io.IOException{
        req.setAttribute("students", students);
        req.getRequestDispatcher("b6.jsp").forward(req, res);
    }


}
