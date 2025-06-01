package com.ss15.controller;

import com.ss15.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController{
    private static final List<Student> STUDENTS = new ArrayList<>(Arrays.asList(
            new Student(1, "Nguyễn Văn A", 18, "a.nguyen@example.com", "12A1", "0123456789"),
            new Student(2, "Trần Thị B", 17, "b.tran@example.com", "11B2", "0987654321"),
            new Student(3, "Lê Văn C", 19, "c.le@example.com", "12C3", "0911223344")
    ));


    @GetMapping
    public String student(Model model){
        model.addAttribute("students", STUDENTS);
        return "b2/students";
    }
}
