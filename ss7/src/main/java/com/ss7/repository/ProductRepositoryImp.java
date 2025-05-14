package com.ss7.repository;

import com.ss7.dao.DBConnect;
import com.ss7.model.Product;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepositoryImp implements ProductRepository{
    @Override
    public List<Product> findAll(){
        Connection con = null;
        CallableStatement cs = null;
        List<Product> list = new ArrayList<>();
        try{

        }catch(Exception e){
            e.fillInStackTrace();
        }finally{
            DBConnect.closeConnection(con, cs);
        }
        return list;
    }

    @Override
    public Product findById(int id){
        return null;
    }

    @Override
    public boolean save(Product product){
        return false;
    }

    @Override
    public boolean deleteById(int id){
        return false;
    }

    @Override
    public boolean update(Product product){
        return false;
    }
}
