package com.example.ss5.controller;

import com.example.ss5.model.Student;
import com.example.ss5.service.studentservice.StudentService;
import com.example.ss5.service.studentservice.StudentServiceImp;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.List;

@WebServlet("/student/*")
public class StudentController extends HttpServlet {
    private final StudentService studentService = new StudentServiceImp();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        String pathInfo = req.getPathInfo();
        if (pathInfo == null) {
            pathInfo = "/list";
        }

        try {
            switch (pathInfo) {
                case "/list":
                    listStudents(req, res);
                    break;
                case "/add":
                    showAddForm(req, res);
                    break;
                case "/edit":
                    showEditForm(req, res);
                    break;
                case "/delete":
                    deleteStudent(req, res);
                    break;
                default:
                    res.sendRedirect(req.getContextPath() + "/student/list");
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            res.sendRedirect("views/error.jsp");
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        String action = req.getParameter("action");
        if (action == null) {
            res.sendRedirect(req.getContextPath() + "/student/list");
            return;
        }

        Student student = new Student();
        try {
            student.setId(Integer.parseInt(req.getParameter("id")));
            student.setName(req.getParameter("name"));
            student.setAge(Integer.parseInt(req.getParameter("age")));
            student.setAddress(req.getParameter("address"));
        } catch (Exception e) {
            student.setName(req.getParameter("name"));
            student.setAddress(req.getParameter("address"));
            req.setAttribute("formError", "Dữ liệu không hợp lệ!");
            req.setAttribute("student", student);
            req.getRequestDispatcher("/views/studentForm.jsp").forward(req, res);
            return;
        }

        if (!validate(student, req)) {
            req.setAttribute("student", student);
            req.getRequestDispatcher("/views/studentForm.jsp").forward(req, res);
            return;
        }

        switch (action) {
            case "confirm":
                confirmStudent(req, res, student);
                break;
            case "save":
                saveStudent(req, res, student);
                break;
            case "update":
                updateStudent(req, res, student);
                break;
            default:
                res.sendRedirect(req.getContextPath() + "/student/list");
                break;
        }
    }

    private void listStudents(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int page = 1;
        try {
            page = Integer.parseInt(req.getParameter("page"));
            if (page < 1) page = 1;
        } catch (Exception e) {
        }
    
        List<Student> students = studentService.findPage(page);
        int totalCount = studentService.count();
        int totalPages = studentService.getTotalPages();
        
        System.out.println("Pagination info - Count: " + totalCount + 
                          ", Pages: " + totalPages + 
                          ", Current: " + page + 
                          ", Students shown: " + students.size());

        totalPages = Math.max(1, totalPages);
        
        req.setAttribute("students", students);
        req.setAttribute("currentPage", page);
        req.setAttribute("totalPages", totalPages);
        req.setAttribute("totalCount", totalCount);
        req.getRequestDispatcher("/views/studentList.jsp").forward(req, res);
    }

    private void showAddForm(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.getRequestDispatcher("/views/studentForm.jsp").forward(req, res);
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        try {
            int id = Integer.parseInt(idStr);
            Student student = studentService.findById(id);
            if (student != null) {
                req.setAttribute("student", student);
                req.getRequestDispatcher("/views/studentEditForm.jsp").forward(req, res);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // If we get here, there was an error
        res.sendRedirect(req.getContextPath() + "/student/list");
    }

    private void deleteStudent(HttpServletRequest req, HttpServletResponse res) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        studentService.delete(id);
        res.sendRedirect(req.getContextPath() + "/student/list");
    }

    private void confirmStudent(HttpServletRequest req, HttpServletResponse res, Student student) throws ServletException, IOException {
        req.setAttribute("student", student);
        req.getRequestDispatcher("/views/studentConfirm.jsp").forward(req, res);
    }

    private void saveStudent(HttpServletRequest req, HttpServletResponse res, Student student) throws ServletException, IOException {
        if (studentService.save(student)) {
            req.setAttribute("student", student);
            req.setAttribute("message", "Student saved successfully!");
            req.setAttribute("redirectUrl", req.getContextPath() + "/student/list");
            req.getRequestDispatcher("/views/studentResult.jsp").forward(req, res);
        } else {
            req.setAttribute("errorMessage", "Failed to save student!");
            req.getRequestDispatcher("/views/error.jsp").forward(req, res);
        }
    }

    private void updateStudent(HttpServletRequest req, HttpServletResponse res, Student student) throws ServletException, IOException {
        if (studentService.update(student)) {
            req.setAttribute("student", student);
            req.setAttribute("message", "Student updated successfully!");
            req.getRequestDispatcher("/views/studentResult.jsp").forward(req, res);
        } else {
            res.sendRedirect("/views/error.jsp");
        }
    }

    private boolean validate(Student student, HttpServletRequest req) {
        boolean isValid = true;

        if (student.getName() == null || student.getName().trim().isEmpty()) {
            req.setAttribute("nameError", "Name cannot be empty!");
            isValid = false;
        }

        if (student.getAge() <= 0) {
            req.setAttribute("ageError", "Age must be greater than 0!");
            isValid = false;
        }

        if (student.getAddress() == null || student.getAddress().trim().isEmpty()) {
            req.setAttribute("addressError", "Address cannot be empty!");
            isValid = false;
        }

        return isValid;
    }
}
