package com.ss15.service;

import com.ss15.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{
    private final List<Product> products;

    public ProductServiceImpl() {
        products = new ArrayList<>();
        products.add(new Product(1L, "SP001", "Laptop Dell", 1500.0, "Laptop gaming cao cấp"));
        products.add(new Product(2L, "SP002", "Iphone 14", 1000.0, "Điện thoại thông minh"));
        products.add(new Product(3L, "SP003", "Tai nghe Sony", 200.0, "Tai nghe không dây"));
    }

    public List<Product> searchProducts(String keyword) {
        return products.stream()
                .filter(p -> p.getName().toLowerCase().contains(keyword.toLowerCase()) ||
                        p.getCode().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }
}
