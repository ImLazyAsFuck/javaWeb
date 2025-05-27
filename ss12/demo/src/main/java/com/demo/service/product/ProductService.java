package com.demo.service.product;

import com.demo.model.Product;

import java.util.List;

public interface ProductService{
    List<Product> getAllProducts();
    Product getProductById(int id);
    boolean createProduct(Product product);
    boolean updateProduct(Product product);
    boolean deleteProduct(int id);
}
