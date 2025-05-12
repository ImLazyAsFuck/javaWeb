package com.example.ss5.service.personservice;

import com.example.ss5.model.Student;

import java.util.List;

public interface StudentService{
    boolean save(Student student);
    List<Student> findPage(int page);
    boolean delete(int id);
    boolean update(Student student);
    int count();
    int getTotalPages();
    List<Student> findAll();
}
