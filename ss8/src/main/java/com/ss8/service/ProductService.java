package com.ss8.service;

import com.ss8.model.Product;

import java.util.List;

public interface ProductService{
    List<Product> findAll();
    boolean save(Product product);
}
