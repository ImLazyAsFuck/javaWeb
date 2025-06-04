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
    public List<Product> findAll(){
        return productRepo.findAll();
    }

    @Override
    public Product findById(int id){
        return productRepo.findById(id);
    }

    @Override
    public long countAll(){
        return productRepo.countAll();
    }

    @Override
    public boolean isExist(String name){
        return productRepo.isExist(name);
    }

    @Override
    public void save(Product product){
        productRepo.save(product);
    }

    @Override
    public void delete(Product product){
        productRepo.delete(product);
    }

    @Override
    public void update(Product product){
        productRepo.update(product);
    }

    @Override
    public List<Product> findByName(String search, int page, int pageSize){
        return productRepo.findByName(search, page, pageSize);
    }

    @Override
    public long countByName(String search){
        return productRepo.countByName(search);
    }

    @Override
    public long countByPrice(Double minPrice, Double maxPrice){
        return  productRepo.countByPrice(minPrice, maxPrice);
    }

    @Override
    public List<Product> findByPrice(Double minPrice, Double maxPrice, int page, int size){
        return productRepo.findByPrice(minPrice, maxPrice, page, size);
    }

    @Override
    public long countByNameAndPrice(String search, Double minPrice, Double maxPrice){
        return productRepo.countByNameAndPrice(search, minPrice, maxPrice);
    }

    @Override
    public List<Product> findByNameAndPrice(String search, Double minPrice, Double maxPrice, int page, int size){
        return productRepo.findByNameAndPrice(search, minPrice, maxPrice, page, size);
    }
}
