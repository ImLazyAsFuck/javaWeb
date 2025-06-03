package com.ss17.repo.productcart;

import com.ss17.entity.ProductCart;

import java.util.List;

public interface ProductCartRepo{
    List<ProductCart> findAll();
    void save(ProductCart productCart);
    void delete(ProductCart productCart);
    ProductCart findById(int id);
    ProductCart findByCustomerIdAndProductId(int customerId, int productId);
    void update(ProductCart productCart);
    List<ProductCart> findByCustomerId(int customerId);
}
