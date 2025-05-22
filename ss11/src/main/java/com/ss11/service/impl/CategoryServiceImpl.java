package com.ss11.service.impl;

import com.ss11.model.Category;
import com.ss11.repository.CategoryRepository;
import com.ss11.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(Integer id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Category findByCategoryName(String categoryName) {
        return categoryRepository.findByCategoryName(categoryName);
    }

    @Override
    public boolean save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public boolean update(Category category) {
        return categoryRepository.update(category);
    }

    @Override
    public boolean delete(Integer id) {
        return categoryRepository.delete(id);
    }
    
    @Override
    public boolean isCategoryNameUnique(String categoryName) {
        Category existingCategory = categoryRepository.findByCategoryName(categoryName);
        return existingCategory == null;
    }
    
    @Override
    public boolean isCategoryNameUnique(String categoryName, Integer id) {
        Category existingCategory = categoryRepository.findByCategoryName(categoryName);
        if (existingCategory == null) {
            return true;
        }
        return existingCategory.getId().equals(id);
    }
}
