package com.ss7.repository;


import com.ss7.model.Product;

import java.util.List;

public interface ProductRepository{
    List<Product> findAll();
    Product findById(int id);
    boolean save(Product product);
    boolean deleteById(int id);
    boolean update(Product product);
}
