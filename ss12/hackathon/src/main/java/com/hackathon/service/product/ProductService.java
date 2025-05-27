package com.hackathon.service.product;

import com.hackathon.model.Product;

import java.util.List;

public interface ProductService{
    List<Product> getAllProducts();
    Product getProductById(int id);
    boolean createProduct(Product product);
    boolean updateProduct(Product product);
    boolean deleteProduct(int id);
    List<Product> findProductByName(String name);
    boolean existsByName(String name);
}
