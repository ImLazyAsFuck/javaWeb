package com.example.ss6.service.productservice;

import com.example.ss6.model.Product;
import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product findById(int id);
    boolean save(Product product);
    boolean update(Product product);
    boolean deleteById(int id);
}
