package com.ss8.controller;

import com.ss8.model.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController{
    private static final List<Employee> EMPLOYEES = new ArrayList<Employee>();
    private static int idSequence = 0;

    @GetMapping
    public ModelAndView showEmployee(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("employee/employee_list");
        modelAndView.addObject("employees", EMPLOYEES);
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView addEmployee(){
        return new ModelAndView("employee/add_employee");
    }

    @PostMapping("/add")
    public ModelAndView addEmployee(Employee employee){
        employee.setId(++idSequence);
        EMPLOYEES.add(employee);
        return new ModelAndView("redirect:/employees");
    }
}
