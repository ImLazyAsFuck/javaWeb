package com.example.ss5.service.studentservice;

import com.example.ss5.dao.persondao.StudentDAO;
import com.example.ss5.dao.persondao.StudentDAOImp;
import com.example.ss5.model.Student;

import java.util.List;

public class StudentServiceImp implements StudentService{
    private final StudentDAO studentDAO = new StudentDAOImp();
    private static final int PAGE_SIZE = 5;

    @Override
    public List<Student> findAll() {
        return studentDAO.findAll();
    }

    @Override
    public boolean save(Student student) {
        return studentDAO.save(student);
    }

    @Override
    public List<Student> findPage(int page) {
        return studentDAO.findPage(page);
    }

    @Override
    public boolean delete(int id) {
        return studentDAO.delete(id);
    }

    @Override
    public boolean update(Student student) {
        return studentDAO.update(student);
    }

    @Override
    public int count() {
        return studentDAO.count();
    }
    
    @Override
    public Student findById(int id) {
        return studentDAO.findById(id);
    }

    @Override
    public int getTotalPages(){
        int studentCount = studentDAO.count();
        System.out.println("Total student count: " + studentCount);

        if (studentCount == 0) return 1;

        return (studentCount % PAGE_SIZE == 0)
                ? (studentCount / PAGE_SIZE)
                : (studentCount / PAGE_SIZE + 1);
    }


}
