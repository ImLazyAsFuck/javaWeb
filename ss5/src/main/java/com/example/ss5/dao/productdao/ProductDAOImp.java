package com.example.ss5.dao.productdao;

import com.example.ss5.model.Product;
import com.example.ss5.utils.DBConnect;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImp implements ProductDAO{

    @Override
    public List<Product> findAll(){
        Connection con = null;
        CallableStatement cs = null;
        List<Product> products = new ArrayList<>();
        try{
            con = DBConnect.getConnection();
            cs = con.prepareCall("{call get_all_product()}");
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                Product product = new Product();
                product.setId(rs.getInt("p_id"));
                product.setName(rs.getString("p_name"));
                product.setPrice(rs.getDouble("p_price"));
                product.setDescription(rs.getString("p_description"));
                products.add(product);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBConnect.closeConnection(cs);
        }
        return products;
    }
}
