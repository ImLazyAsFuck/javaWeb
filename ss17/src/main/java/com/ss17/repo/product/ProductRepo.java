package com.ss17.repo.product;

import com.ss17.entity.Product;
import com.ss17.entity.ProductCart;

import java.util.List;

public interface ProductRepo{
    List<Product> findAll(int page, int pageSize);
    Product findById(int id);
    long countAll();

}
