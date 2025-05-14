package com.ss7.service;

import com.ss7.model.Product;
import com.ss7.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAll(){
        return productRepository.findAll();
    }

    @Override
    public Product findById(int id){
        return productRepository.findById(id);
    }

    @Override
    public boolean save(Product product){
        return productRepository.save(product);
    }

    @Override
    public boolean deleteById(int id){
        return productRepository.deleteById(id);
    }

    @Override
    public boolean update(Product product){
        return false;
    }
}
