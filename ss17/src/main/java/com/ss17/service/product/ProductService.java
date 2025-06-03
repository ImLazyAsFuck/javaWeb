package com.ss17.service.product;

import com.ss17.entity.Product;

import java.util.List;

public interface ProductService{
    List<Product> findAll(int page, int pageSize);
    Product findById(int id);
    long countAll();
}
