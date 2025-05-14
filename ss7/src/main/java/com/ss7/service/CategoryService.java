package com.ss7.service;

import com.ss7.model.Category;
import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    Category findById(int id);
    boolean save(Category category);
    boolean deleteById(int id);
    boolean update(Category category);
}
