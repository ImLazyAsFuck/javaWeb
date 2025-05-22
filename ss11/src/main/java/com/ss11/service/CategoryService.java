package com.ss11.service;

import com.ss11.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    Category findById(Integer id);
    Category findByCategoryName(String categoryName);
    boolean save(Category category);
    boolean update(Category category);
    boolean delete(Integer id);
    boolean isCategoryNameUnique(String categoryName);
    boolean isCategoryNameUnique(String categoryName, Integer id);
}
