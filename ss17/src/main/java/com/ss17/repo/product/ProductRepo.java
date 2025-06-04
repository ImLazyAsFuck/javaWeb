package com.ss17.repo.product;

import com.ss17.entity.Product;

import java.util.List;

public interface ProductRepo{
    List<Product> findAll(int page, int pageSize);
    List<Product> findAll();
    Product findById(int id);
    long countAll();
    boolean isExist(String name);
    void save(Product product);
    void delete(Product product);
    void update(Product product);
    List<Product> findByName(String search, int page, int pageSize);
    long countByName(String search);
    long countByPrice(Double minPrice, Double maxPrice);
    List<Product> findByPrice(Double minPrice, Double maxPrice, int page, int size);
    long countByNameAndPrice(String search, Double minPrice, Double maxPrice);
    List<Product> findByNameAndPrice(String search, Double minPrice, Double maxPrice, int page, int size);
}
