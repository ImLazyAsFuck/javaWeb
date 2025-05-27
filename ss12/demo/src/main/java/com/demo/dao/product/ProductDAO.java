package com.demo.dao.product;

import com.demo.model.Product;

import java.util.List;

public interface ProductDAO{
    List<Product> findAllProducts();
    Product findProductById(int id);
    boolean saveProduct(Product product);
    boolean updateProduct(Product product);
    boolean deleteProduct(int id);
}
