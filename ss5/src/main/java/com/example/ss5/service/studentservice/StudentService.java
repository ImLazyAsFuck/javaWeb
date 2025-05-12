package com.example.ss5.service.studentservice;

import com.example.ss5.model.Student;

import java.util.List;

public interface StudentService {
    List<Student> findAll();
    boolean save(Student student);
    List<Student> findPage(int page);
    boolean delete(int id);
    boolean update(Student student);
    int count();
    Student findById(int id);
    int getTotalPages();
}
