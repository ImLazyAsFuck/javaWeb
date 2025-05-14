package com.ss7.service;

import com.ss7.model.Category;
import com.ss7.repository.CategoryRepository;
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
    public Category findById(int id) {
        return categoryRepository.findById(id);
    }
    
    @Override
    public boolean save(Category category) {
        return categoryRepository.save(category);
    }
    
    @Override
    public boolean deleteById(int id) {
        return categoryRepository.deleteById(id);
    }
    
    @Override
    public boolean update(Category category) {
        return categoryRepository.update(category);
    }
}
