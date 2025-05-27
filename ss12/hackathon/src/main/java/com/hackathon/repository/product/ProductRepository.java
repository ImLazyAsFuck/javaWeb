package com.hackathon.repository.product;

import com.hackathon.model.Product;

import java.util.List;

public interface ProductRepository{
    List<Product> findAllProducts();
    Product findProductById(int id);
    boolean saveProduct(Product product);
    boolean updateProduct(Product product);
    boolean deleteProduct(int id);
    List<Product> findProductByName(String name);
    boolean existsByName(String name);
}
