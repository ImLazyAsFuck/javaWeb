package com.demo.dao.product;

import com.demo.model.Product;
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
public class ProductDAOImpl implements ProductDAO{
    @Autowired
    private DataSource dataSource;

    @Override
    public List<Product> findAllProducts(){
        Connection con = null;
        CallableStatement cs = null;
        List<Product> products = new ArrayList<>();
        try{
            con = dataSource.getConnection();
            cs = con.prepareCall("{call find_all_product()}");
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setQuantity(rs.getInt("quantity"));
                products.add(product);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(con != null) con.close();
                if(cs != null) cs.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return products;
    }

    @Override
    public Product findProductById(int id){
        Connection con = null;
        CallableStatement cs = null;
        Product product = null;
        try{
            con = dataSource.getConnection();
            cs = con.prepareCall("{call find_product_by_id(?)}");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            if(rs.next()){
                product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setQuantity(rs.getInt("quantity"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
                if(con != null) con.close();
                if(cs != null) cs.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return product;
    }

    @Override
    public boolean saveProduct(Product product){
        Connection con = null;
        CallableStatement cs = null;
        try{
            con = dataSource.getConnection();
            cs = con.prepareCall("{call save_product(?, ?, ?)}");
            cs.setString(1, product.getName());
            cs.setDouble(2, product.getPrice());
            cs.setInt(3, product.getQuantity());
            int rowsAffected = cs.executeUpdate();
            return rowsAffected > 0;
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(cs != null) cs.close();
                if(con != null) con.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean updateProduct(Product product){
        Connection con = null;
        CallableStatement cs = null;
        try{
            con = dataSource.getConnection();
            cs = con.prepareCall("{call update_product(?, ?, ?, ?)}");
            cs.setInt(1, product.getId());
            cs.setString(2, product.getName());
            cs.setDouble(3, product.getPrice());
            cs.setInt(4, product.getQuantity());
            int rowsAffected = cs.executeUpdate();
            return rowsAffected > 0;
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(cs != null) cs.close();
                if(con != null) con.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean deleteProduct(int id){
        Connection con = null;
        CallableStatement cs = null;
        try{
            con = dataSource.getConnection();
            cs = con.prepareCall("{call delete_product(?)}");
            cs.setInt(1, id);
            int rowsAffected = cs.executeUpdate();
            return rowsAffected > 0;
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(cs != null) cs.close();
                if(con != null) con.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return false;
    }
}
