package com.ss8.repository;

import com.ss8.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ProductRepository{
    List<Product> findAll();
    boolean save(Product product);
}
