package com.hackathon.service.product;

import com.hackathon.repository.product.ProductRepository;
import com.hackathon.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImp implements ProductService{
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts(){
        return productRepository.findAllProducts();
    }

    @Override
    public Product getProductById(int id){
        return productRepository.findProductById(id);
    }

    @Override
    public boolean createProduct(Product product){
        return productRepository.saveProduct(product);
    }

    @Override
    public boolean updateProduct(Product product){
        return productRepository.updateProduct(product);
    }

    @Override
    public boolean deleteProduct(int id){
        return productRepository.deleteProduct(id);
    }

    @Override
    public List<Product> findProductByName(String name){
        return productRepository.findProductByName(name);
    }

    @Override
    public boolean existsByName(String name){
        return productRepository.existsByName(name);
    }
}
