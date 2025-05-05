package com.mystorage.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/StudentTicketServlet")
public class StudentTicketServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;

    private List<Student> students = new ArrayList<>();

    @Override
    public void init() throws ServletException{
        students.add(new Student("Nguyễn Văn An", "CNTT01", "Xe máy", "29H1-12345"));
        students.add(new Student("Trần Thị Bình", "CNTT02", "Xe đạp", "N/A"));
        students.add(new Student("Lê Minh Châu", "KTPM01", "Xe máy", "29H1-67890"));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        request.setAttribute("studentList", students);
        request.getRequestDispatcher("/listStudent.jsp").forward(request, response);
    }


    public static class Student {
        private String fullName;
        private String className;
        private String vehicleType;
        private String licensePlate;

        public Student(String fullName, String className, String vehicleType, String licensePlate) {
            this.fullName = fullName;
            this.className = className;
            this.vehicleType = vehicleType;
            this.licensePlate = licensePlate;
        }

        public String getFullName() { return fullName; }
        public String getClassName() { return className; }
        public String getVehicleType() { return vehicleType; }
        public String getLicensePlate() { return licensePlate; }
    }
}