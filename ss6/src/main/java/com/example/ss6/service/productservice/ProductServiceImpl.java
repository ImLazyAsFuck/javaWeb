package com.example.ss6.service.productservice;

import com.example.ss6.dao.productdao.ProductDAO;
import com.example.ss6.dao.productdao.ProductDAOImpl;
import com.example.ss6.model.Product;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private final ProductDAO PRODUCT_DAO = new ProductDAOImpl();

    @Override
    public List<Product> findAll() {
        return PRODUCT_DAO.findAll();
    }

    @Override
    public Product findById(int id) {
        return PRODUCT_DAO.findById(id);
    }

    @Override
    public boolean save(Product product) {
        return PRODUCT_DAO.save(product);
    }

    @Override
    public boolean update(Product product) {
        return PRODUCT_DAO.update(product);
    }

    @Override
    public boolean deleteById(int id) {
        return PRODUCT_DAO.deleteById(id);
    }
}
