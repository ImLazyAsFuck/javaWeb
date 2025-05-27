package com.demo.controller;

import com.demo.model.Student;
import com.demo.service.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/student")
public class StudentController{
    @Autowired
    private StudentService studentService;

    @GetMapping
    public String list(Model model){
        model.addAttribute("students", studentService.getAllStudents());
        return "b1/student_list";
    }

    @GetMapping("add")
    public String add(@ModelAttribute Student student, Model model){
        model.addAttribute("student", student);
        return "b1/add_student";
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable int id, Model model){
        Student student = studentService.getStudentById(id);
        if(student == null){
            return "redirect:/student";
        }
        model.addAttribute("student", student);
        return "b1/edit_student";
    }

    @PostMapping("add")
    public String save(@Valid @ModelAttribute Student student, BindingResult result){
        if(result.hasErrors()){
            return "b1/add_student";
        }
        studentService.saveStudent(student);
        return "redirect:/student";
    }

    @PostMapping("edit")
    public String update(@Valid @ModelAttribute Student student, BindingResult result){
        if(result.hasErrors()){
            return "b1/edit_student";
        }
        studentService.updateStudent(student);
        return "redirect:/student";
    }

    @GetMapping("delete/{id}")
    public String delete(@ModelAttribute Student student){
        studentService.deleteStudent(student.getId());
        return "redirect:/student";
    }
}
