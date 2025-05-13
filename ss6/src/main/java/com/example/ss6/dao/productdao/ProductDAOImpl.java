package com.example.ss6.dao.productdao;

import com.example.ss6.model.Product;
import com.example.ss6.utils.DBConnect;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {
    @Override
    public List<Product> findAll() {
        Connection con = null;
        CallableStatement cs = null;
        List<Product> products = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            cs = con.prepareCall("{call find_all_products()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                products.add(extractProductFromResultSet(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeConnection(con, cs);
        }
        return products;
    }

    @Override
    public Product findById(int id) {
        Connection con = null;
        CallableStatement cs = null;
        Product product = null;
        try {
            con = DBConnect.getConnection();
            cs = con.prepareCall("{call find_product_by_id(?)}");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                product = extractProductFromResultSet(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeConnection(con, cs);
        }
        return product;
    }

    @Override
    public boolean save(Product product) {
        Connection con = null;
        CallableStatement cs = null;
        try {
            con = DBConnect.getConnection();
            cs = con.prepareCall("{call add_product(?, ?, ?)}");
            cs.setString(1, product.getName());
            cs.setDouble(2, product.getPrice());
            cs.setString(3, product.getImageUrl());
            
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                product.setId(id);
                return id > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeConnection(con, cs);
        }
        return false;
    }

    @Override
    public boolean update(Product product) {
        Connection con = null;
        CallableStatement cs = null;
        try {
            con = DBConnect.getConnection();
            cs = con.prepareCall("{call update_product(?, ?, ?, ?)}");
            cs.setInt(1, product.getId());
            cs.setString(2, product.getName());
            cs.setDouble(3, product.getPrice());
            cs.setString(4, product.getImageUrl());
            
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                return rs.getInt("affected_rows") > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeConnection(con, cs);
        }
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        Connection con = null;
        CallableStatement cs = null;
        try {
            con = DBConnect.getConnection();
            cs = con.prepareCall("{call delete_product(?)}");
            cs.setInt(1, id);
            
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                return rs.getInt("affected_rows") > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeConnection(con, cs);
        }
        return false;
    }

    private Product extractProductFromResultSet(ResultSet rs) throws Exception {
        Product product = new Product();
        product.setId(rs.getInt("id"));
        product.setName(rs.getString("name"));
        product.setPrice(rs.getDouble("price"));
        product.setImageUrl(rs.getString("image_url"));
        return product;
    }
}
