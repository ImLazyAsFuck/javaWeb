package com.example.category.service.categoryservice;

import com.example.category.dao.categorydao.CategoryDAO;
import com.example.category.dao.categorydao.CategoryDAOImp;
import com.example.category.model.category.Category;

import java.sql.SQLException;
import java.util.List;

public class CategoryServiceImp implements CategoryService{
    private final CategoryDAO CATEGORY_DAO = new CategoryDAOImp();

    @Override
    public List<Category> findAll() throws SQLException{
        return CATEGORY_DAO.findAll();
    }

    @Override
    public Category findById(int id){
        return CATEGORY_DAO.findById(id);
    }

    @Override
    public boolean save(Category category){
        return CATEGORY_DAO.save(category);
    }

    @Override
    public boolean update(Category category){
        return CATEGORY_DAO.update(category);
    }

    @Override
    public boolean delete(int id){
        return CATEGORY_DAO.delete(id);
    }
}
