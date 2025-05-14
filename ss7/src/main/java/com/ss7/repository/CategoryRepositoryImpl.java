package com.ss7.repository;

import com.ss7.model.Category;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {
    
    // This is a mock implementation for demo purposes
    // In a real application, this would connect to a database
    
    private static final List<Category> categories = new ArrayList<>();
    
    static {
        // Initialize with some categories
        categories.add(new Category(1, "Electronics"));
        categories.add(new Category(2, "Clothing"));
        categories.add(new Category(3, "Books"));
        categories.add(new Category(4, "Home & Kitchen"));
        categories.add(new Category(5, "Sports"));
    }
    
    @Override
    public List<Category> findAll() {
        return new ArrayList<>(categories);
    }
    
    @Override
    public Category findById(int id) {
        return categories.stream()
                .filter(category -> category.getId() == id)
                .findFirst()
                .orElse(null);
    }
    
    @Override
    public boolean save(Category category) {
        // Generate a new ID
        int newId = categories.stream()
                .mapToInt(Category::getId)
                .max()
                .orElse(0) + 1;
        
        category.setId(newId);
        categories.add(category);
        return true;
    }
    
    @Override
    public boolean deleteById(int id) {
        return categories.removeIf(category -> category.getId() == id);
    }
    
    @Override
    public boolean update(Category category) {
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).getId() == category.getId()) {
                categories.set(i, category);
                return true;
            }
        }
        return false;
    }
}
