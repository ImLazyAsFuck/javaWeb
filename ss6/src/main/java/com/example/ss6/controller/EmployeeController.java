package com.example.ss6.controller;

import com.example.ss6.model.Employee;
import com.example.ss6.service.employeeservice.EmployeeService;
import com.example.ss6.service.employeeservice.EmployeeServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet("/employees")
public class EmployeeController extends HttpServlet {
    private final EmployeeService EMPLOYEE_SERVICE = new EmployeeServiceImpl();
    private static final int DEFAULT_PAGE_SIZE = 5;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null || action.isEmpty()) {
            listEmployees(req, res);
            return;
        }

        switch (action) {
            case "add" -> showAddForm(req, res);
            case "edit" -> showEditForm(req, res);
            case "delete" -> deleteEmployee(req, res);
            case "search" -> searchEmployees(req, res);
            default -> listEmployees(req, res);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null || action.isEmpty()) {
            listEmployees(req, res);
            return;
        }

        switch (action) {
            case "create" -> createEmployee(req, res);
            case "update" -> updateEmployee(req, res);
            default -> listEmployees(req, res);
        }
    }

    private void listEmployees(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int page = getIntParameter(req, "page", 1);
        int pageSize = getIntParameter(req, "size", DEFAULT_PAGE_SIZE);
        String sortField = req.getParameter("sort");
        String sortDirection = req.getParameter("direction");

        if (sortField == null || sortField.isEmpty()) {
            sortField = "id";
        }

        if (sortDirection == null || sortDirection.isEmpty()) {
            sortDirection = "ASC";
        }

        Map<String, Object> pageData = EMPLOYEE_SERVICE.getPagedEmployees(page, pageSize, sortField, sortDirection);

        req.setAttribute("pageData", pageData);
        req.getRequestDispatcher("/views/employee/employeeList.jsp").forward(req, res);
    }

    private void showAddForm(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.getRequestDispatcher("/views/employee/addEmployee.jsp").forward(req, res);
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int id = getIntParameter(req, "id", 0);
        Employee employee = EMPLOYEE_SERVICE.findById(id);

        if (employee != null) {
            req.setAttribute("employee", employee);
            req.getRequestDispatcher("/views/employee/updateEmployee.jsp").forward(req, res);
        } else {
            res.sendRedirect("/employees");
        }
    }

    private void createEmployee(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String name = req.getParameter("name");
        String birthdayStr = req.getParameter("birthday");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        double salary = Double.parseDouble(req.getParameter("salary"));
        String position = req.getParameter("position");

        LocalDate birthday = LocalDate.parse(birthdayStr);

        Employee employee = new Employee();
        employee.setName(name);
        employee.setBirthday(birthday);
        employee.setPhone(phone);
        employee.setEmail(email);
        employee.setSalary(salary);
        employee.setPosition(position);

        EMPLOYEE_SERVICE.save(employee);
        res.sendRedirect("/employees");
    }

    private void updateEmployee(HttpServletRequest req, HttpServletResponse res) throws IOException {
        int id = getIntParameter(req, "id", 0);
        String name = req.getParameter("name");
        String birthdayStr = req.getParameter("birthday");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        double salary = Double.parseDouble(req.getParameter("salary"));
        String position = req.getParameter("position");

        LocalDate birthday = LocalDate.parse(birthdayStr);

        Employee employee = new Employee();
        employee.setId(id);
        employee.setName(name);
        employee.setBirthday(birthday);
        employee.setPhone(phone);
        employee.setEmail(email);
        employee.setSalary(salary);
        employee.setPosition(position);

        EMPLOYEE_SERVICE.update(employee);
        res.sendRedirect("/employees");
    }

    private void deleteEmployee(HttpServletRequest req, HttpServletResponse res) throws IOException {
        int id = getIntParameter(req, "id", 0);
        EMPLOYEE_SERVICE.deleteById(id);
        res.sendRedirect("/employees");
    }

    private void searchEmployees(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String searchType = req.getParameter("searchType");
        List<Employee> employees;

        if ("id".equals(searchType)) {
            String idKeyword = req.getParameter("idKeyword");
            if (idKeyword != null && !idKeyword.isEmpty()) {
                try {
                    int id = Integer.parseInt(idKeyword);
                    Employee employee = EMPLOYEE_SERVICE.findById(id);
                    employees = new ArrayList<>();
                    if (employee != null) {
                        employees.add(employee);
                    }
                } catch (NumberFormatException e) {
                    employees = EMPLOYEE_SERVICE.findAll();
                }
            } else {
                employees = EMPLOYEE_SERVICE.findAll();
            }
        } else {
            String nameKeyword = req.getParameter("nameKeyword");
            if (nameKeyword != null && !nameKeyword.isEmpty()) {
                employees = EMPLOYEE_SERVICE.findByName(nameKeyword);
            } else {
                employees = EMPLOYEE_SERVICE.findAll();
            }
        }

        req.setAttribute("employees", employees);
        req.setAttribute("searchPerformed", true);
        req.getRequestDispatcher("/views/employee/employeeList.jsp").forward(req, res);
    }

    private int getIntParameter(HttpServletRequest req, String paramName, int defaultValue) {
        String paramValue = req.getParameter(paramName);
        if (paramValue == null || paramValue.isEmpty()) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(paramValue);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
