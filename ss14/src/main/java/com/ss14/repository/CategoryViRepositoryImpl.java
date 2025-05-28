package com.ss14.repository;

import com.ss14.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoryViRepositoryImpl implements CategoryViRepository {
    @Autowired
    private DataSource dataSource;

    @Override
    public void save(Category category) {
        try (Connection connection = dataSource.getConnection();
             CallableStatement stmt = connection.prepareCall("{CALL save_categories_vi(?, ?)}")) {
            stmt.setString(1, category.getCategoryName());
            stmt.setString(2, category.getDescription());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error saving category in Vietnamese: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Category> findAll() {
        List<Category> categories = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             CallableStatement stmt = connection.prepareCall("{CALL  find_all_categories_vi()}");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Category category = new Category(
                        rs.getInt("id"),
                        rs.getString("categoryName"),
                        rs.getString("description")
                );
                categories.add(category);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching categories: " + e.getMessage(), e);
        }
        return categories;
    }
}