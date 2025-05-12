package com.example.ss5.service.personservice;

import com.example.ss5.model.Student;

import com.example.ss5.dao.persondao.StudentDAO;
import com.example.ss5.dao.persondao.StudentDAOImp;

import java.util.List;

public class StudentServiceImp implements StudentService{
    private final StudentDAO studentDAO = new StudentDAOImp();
    
    @Override
    public boolean save(Student student){
        return studentDAO.save(student);
    }

    @Override
    public List<Student> findPage(int page){
        return studentDAO.findPage(page);
    }

    @Override
    public boolean delete(int id){
        return studentDAO.delete(id);
    }

    @Override
    public boolean update(Student student){
        return studentDAO.update(student);
    }

    @Override
    public int count(){
        return studentDAO.count();
    }

    private static final int PAGE_SIZE = 5;
    
    @Override
    public int getTotalPages(){
        int studentCount = studentDAO.count();
        System.out.println("Total student count: " + studentCount);
        
        if (studentCount == 0) return 1; // Always have at least 1 page even if empty
        
        return (studentCount % PAGE_SIZE == 0) 
               ? (studentCount / PAGE_SIZE) 
               : (studentCount / PAGE_SIZE + 1);
    }

    @Override
    public List<Student> findAll(){
        return studentDAO.findAll();
    }
}
