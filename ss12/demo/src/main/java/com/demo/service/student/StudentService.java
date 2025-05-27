package com.demo.service.student;

import com.demo.model.Student;

import java.util.List;

public interface StudentService{
    List<Student> getAllStudents();
    Student getStudentById(int id);
    boolean saveStudent(Student student);
    boolean updateStudent(Student student);
    boolean deleteStudent(int id);
}
