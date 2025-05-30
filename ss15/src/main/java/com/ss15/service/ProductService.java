package com.ss15.service;

import com.ss15.model.Product;

import java.util.List;

public interface ProductService{
    List<Product> searchProducts(String keyword);
}
