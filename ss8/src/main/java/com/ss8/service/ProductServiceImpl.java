package com.ss8.service;

import com.ss8.model.Product;
import com.ss8.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    ProductRepository productRepository;


    @Override
    public List<Product> findAll(){
        return productRepository.findAll();
    }

    @Override
    public boolean save(Product product){
        return productRepository.save(product);
    }
}
