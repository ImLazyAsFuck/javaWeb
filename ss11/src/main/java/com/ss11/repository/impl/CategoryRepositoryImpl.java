package com.ss11.repository.impl;

import com.ss11.model.Category;
import com.ss11.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

    @Autowired
    private DataSource dataSource;

    @Override
    public List<Category> findAll() {
        List<Category> categories = new ArrayList<>();
        Connection con = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            con = dataSource.getConnection();
            cs = con.prepareCall("{call sp_get_all_categories()}");
            rs = cs.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setCategoryName(rs.getString("categoryName"));
                category.setStatus(rs.getBoolean("status"));
                categories.add(category);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rs, cs, con);
        }
        return categories;
    }

    @Override
    public Category findById(Integer id) {
        Connection con = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            con = dataSource.getConnection();
            cs = con.prepareCall("{call sp_get_category_by_id(?)}");
            cs.setInt(1, id);
            rs = cs.executeQuery();
            if (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setCategoryName(rs.getString("categoryName"));
                category.setStatus(rs.getBoolean("status"));
                return category;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rs, cs, con);
        }
        return null;
    }

    @Override
    public Category findByCategoryName(String categoryName) {
        Connection con = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            con = dataSource.getConnection();
            cs = con.prepareCall("{call sp_get_category_by_name(?)}");
            cs.setString(1, categoryName);
            rs = cs.executeQuery();
            if (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setCategoryName(rs.getString("categoryName"));
                category.setStatus(rs.getBoolean("status"));
                return category;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rs, cs, con);
        }
        return null;
    }

    @Override
    public boolean save(Category category) {
        Connection con = null;
        CallableStatement cs = null;
        try {
            con = dataSource.getConnection();
            cs = con.prepareCall("{call sp_insert_category(?, ?, ?)}");
            cs.setString(1, category.getCategoryName());
            cs.setBoolean(2, category.isStatus());
            cs.registerOutParameter(3, Types.INTEGER);
            cs.execute();
            int id = cs.getInt(3);
            category.setId(id);
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(null, cs, con);
        }
    }

    @Override
    public boolean update(Category category) {
        Connection con = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            con = dataSource.getConnection();
            cs = con.prepareCall("{call sp_update_category(?, ?, ?)}");
            cs.setInt(1, category.getId());
            cs.setString(2, category.getCategoryName());
            cs.setBoolean(3, category.isStatus());
            rs = cs.executeQuery();
            if (rs.next()) {
                int affected = rs.getInt("affected_rows");
                return affected > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rs, cs, con);
        }
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        Connection con = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            con = dataSource.getConnection();
            cs = con.prepareCall("{call sp_delete_category(?)}");
            cs.setInt(1, id);
            rs = cs.executeQuery();
            if (rs.next()) {
                int affected = rs.getInt("affected_rows");
                return affected > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rs, cs, con);
        }
        return false;
    }

    private void close(ResultSet rs, CallableStatement cs, Connection con) {
        try {
            if (rs != null) rs.close();
        } catch (SQLException e) {
        }
        try {
            if (cs != null) cs.close();
        } catch (SQLException e) {
        }
        try {
            if (con != null) con.close();
        } catch (SQLException e) {
        }
    }
}
