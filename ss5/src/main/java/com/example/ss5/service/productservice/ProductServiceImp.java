package com.example.ss5.service.productservice;

import com.example.ss5.dao.productdao.ProductDAO;
import com.example.ss5.dao.productdao.ProductDAOImp;
import com.example.ss5.model.Product;

import java.util.List;

public class ProductServiceImp implements ProductService{
    private final ProductDAO PRODUCT_DAO = new ProductDAOImp();

    @Override
    public List<Product> findAll(){
        return PRODUCT_DAO.findAll();
    }
}
