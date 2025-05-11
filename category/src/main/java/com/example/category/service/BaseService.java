package com.example.category.service;

import com.example.category.model.category.Category;

import java.sql.SQLException;
import java.util.List;

public interface BaseService<T>{
    List<Category> findAll() throws SQLException;
    Category findById(int id);
    boolean save(T t);
    boolean update(T t);
    boolean delete(int id);
}
