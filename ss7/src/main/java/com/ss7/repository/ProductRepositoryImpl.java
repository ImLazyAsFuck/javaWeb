package com.ss7.repository;

import com.ss7.model.Product;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    
    private static final List<Product> products = new ArrayList<>();
    
    static {
        products.add(new Product(1, "Laptop Dell XPS 13", 1299.99, 10,
                "High-performance laptop with 13-inch display, Intel Core i7, 16GB RAM, 512GB SSD",
                "https://images.unsplash.com/photo-1593642632823-8f785ba67e45", 1));
                
        products.add(new Product(2, "iPhone 15 Pro", 999.99, 15,
                "Latest iPhone with A16 Bionic chip, 6.1-inch Super Retina XDR display, 128GB storage",
                "https://images.unsplash.com/photo-1691293441848-b764cf05d952", 1));
                
        products.add(new Product(3, "T-Shirt", 29.99, 50,
                "Cotton t-shirt, comfortable for everyday wear",
                "https://images.unsplash.com/photo-1581655353564-df123a1eb820", 2));
                
        products.add(new Product(4, "Jeans", 59.99, 30,
                "Classic blue jeans with modern fit",
                "https://images.unsplash.com/photo-1542272604-787c3835535d", 2));
                
        products.add(new Product(5, "Harry Potter Complete Set", 120.99, 7,
                "Complete set of all Harry Potter books",
                "https://images.unsplash.com/photo-1551269901-5c5e14c25df7", 3));
    }
    
    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products);
    }
    
    @Override
    public Product findById(int id) {
        return products.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElse(null);
    }
    
    @Override
    public boolean save(Product product) {
        // Generate a new ID
        int newId = products.stream()
                .mapToInt(Product::getId)
                .max()
                .orElse(0) + 1;
        
        product.setId(newId);
        products.add(product);
        return true;
    }
    
    @Override
    public boolean deleteById(int id) {
        return products.removeIf(product -> product.getId() == id);
    }
    
    @Override
    public boolean update(Product product) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == product.getId()) {
                products.set(i, product);
                return true;
            }
        }
        return false;
    }
}
