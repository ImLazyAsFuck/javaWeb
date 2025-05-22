package com.ss11.repository;

import com.ss11.model.Category;

import java.util.List;

public interface CategoryRepository {
    List<Category> findAll();
    Category findById(Integer id);
    Category findByCategoryName(String categoryName);
    boolean save(Category category);
    boolean update(Category category);
    boolean delete(Integer id);
}
