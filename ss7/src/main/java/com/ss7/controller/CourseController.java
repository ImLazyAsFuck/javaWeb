package com.ss7.controller;

import com.ss7.model.Course;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/course")
class CourseController{
    private List<Course> courses = new ArrayList<>();
    private int courseIdCounter = 1;

    @GetMapping
    public String listCourses(Model model) {
        model.addAttribute("courses", courses);
        return "/listCourse";
    }

    @GetMapping("/add")
    public String showAddCourseForm(Model model) {
        model.addAttribute("course", new Course(0, "", ""));
        return "/addCourse";
    }

    @PostMapping("/add")
    public String addCourse(@ModelAttribute Course course) {
        course.setId(courseIdCounter++);
        courses.add(course);
        return "redirect:/course";
    }

    @GetMapping("delete/{id}")
    public String deleteCourse(@PathVariable int id) {
        courses.removeIf(course -> course.getId() == id);
        return "redirect:/course";
    }
}
