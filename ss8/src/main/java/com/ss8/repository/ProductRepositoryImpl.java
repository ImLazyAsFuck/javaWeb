package com.ss8.repository;

import com.ss8.model.Product;
import com.ss8.utils.DBConnect;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository{
    public List<Product> findAll(){
        Connection con = null;
        CallableStatement cs = null;
        List<Product> list = new ArrayList<>();
        try{
            con = DBConnect.getConnection();
            cs = con.prepareCall("{call find_all_product()}");
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                Product product = new Product();
                product.setId(cs.getResultSet().getInt("id"));
                product.setName(cs.getResultSet().getString("name"));
                product.setPrice(cs.getResultSet().getDouble("price"));
                product.setStock(cs.getResultSet().getInt("stock"));
                list.add(product);
            }
        }catch(Exception e){
            e.fillInStackTrace();
        }finally{
            DBConnect.closeConnection(con, cs);
        }
        return list;
    }

    @Override
    public boolean save(Product product){
        Connection con = null;
        CallableStatement cs = null;
        try{
            con = DBConnect.getConnection();
            cs = con.prepareCall("{call save_product(?,?,?)}");
            cs.setString(1, product.getName());
            cs.setDouble(2, product.getPrice());
            cs.setInt(3, product.getStock());
            cs.executeQuery();
        }catch(Exception e){
            e.fillInStackTrace();
        }finally{
            DBConnect.closeConnection(con, cs);
        }
        return false;
    }
}
