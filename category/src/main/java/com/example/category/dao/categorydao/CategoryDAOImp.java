package com.example.category.dao.categorydao;

import com.example.category.model.category.CatalogStatus;
import com.example.category.model.category.Category;
import com.example.category.utils.DBConnect;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOImp implements CategoryDAO{
    @Override
    public List<Category> findAll(){
        Connection con = null;
        CallableStatement cs = null;
        List<Category> categories = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            cs = con.prepareCall("{call find_all_category()}");
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                Category category = new Category();
                category.setId(rs.getInt("c_id"));
                category.setName(rs.getString("c_name"));
                category.setDescription(rs.getString("c_description"));
                category.setStatus(CatalogStatus.valueOf(rs.getString("c_status")));
                categories.add(category);
            }
        }catch (SQLException e){
            System.err.println("Error while find all categories " + e.getMessage());
        }catch(Exception e){
            System.err.println("Unknown error while find all categories " + e.getMessage());
        }finally{
            DBConnect.closeConnection(cs);
        }
        return categories;
    }

    @Override
    public Category findById(int id){
        Connection con = null;
        CallableStatement cs = null;
        Category category = null;
        try {
            con = DBConnect.getConnection();
            cs = con.prepareCall("{call find_category_by_id(?)}");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                category = new Category();
                category.setId(rs.getInt("c_id"));
                category.setName(rs.getString("c_name"));
                category.setDescription(rs.getString("c_description"));
                category.setStatus(CatalogStatus.valueOf(rs.getString("c_status")));
            }
        }catch(SQLException e){
            System.err.println("Error while find category by id " + e.getMessage());
        }catch(Exception e){
            System.err.println("Unknown error while find category by id " + e.getMessage());
        }finally {
            DBConnect.closeConnection(cs);
        }
        return category;
    }

    @Override
    public boolean save(Category category){
        Connection con = null;
        CallableStatement cs = null;
        boolean result = false;
        try {
            con = DBConnect.getConnection();
            cs = con.prepareCall("{call save_category(?,?,?)}");
            cs.setString(2, category.getName());
            cs.setString(3, category.getDescription());
            cs.setString(4, category.getStatus().name());
            result = cs.execute();
        }catch (SQLException e){
            System.err.println("Error while save category " + e.getMessage());
        }catch(Exception e){
            System.err.println("Unknown error while save category " + e.getMessage());
        }
        finally {
            DBConnect.closeConnection(cs);
        }
        return result;
    }

    @Override
    public boolean update(Category category){
        Connection con = null;
        CallableStatement cs = null;
        boolean result = false;
        try {
            con = DBConnect.getConnection();
            cs = con.prepareCall("{call update_category(?,?,?,?)}");
            cs.setInt(1, category.getId());
            cs.setString(2, category.getName());
            cs.setString(3, category.getDescription());
            cs.setString(4, category.getStatus().name());
            result = cs.execute();
        }catch (SQLException e){
            System.err.println("Error while update category " + e.getMessage());
        }catch(Exception e){
            System.err.println("Unknown error while update category " + e.getMessage());
        }finally {
            DBConnect.closeConnection(cs);
        }
        return result;
    }

    @Override
    public boolean delete(int id){
        Connection con = null;
        CallableStatement cs = null;
        boolean result = false;
        try {
            con = DBConnect.getConnection();
            cs = con.prepareCall("{call delete_category(?)}");
            cs.setInt(1, id);
            int affectedRows = cs.executeUpdate();
            result = affectedRows > 0;
        }catch (SQLException e){
            System.err.println("Error while delete category " + e.getMessage());
        }catch(Exception e){
            System.err.println("Unknown error while delete category" + e.getMessage());
        }finally {
            DBConnect.closeConnection(cs);
        }
        return result;
    }
}
