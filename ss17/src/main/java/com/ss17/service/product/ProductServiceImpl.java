package com.ss17.service.product;

import com.ss17.entity.Product;
import com.ss17.repo.product.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepo productRepo;

    @Override
    public List<Product> findAll(int page, int pageSize){
        return productRepo.findAll(page, pageSize);
    }

    @Override
    public Product findById(int id){
        return productRepo.findById(id);
    }

    @Override
    public long countAll(){
        return productRepo.countAll();
    }
}
