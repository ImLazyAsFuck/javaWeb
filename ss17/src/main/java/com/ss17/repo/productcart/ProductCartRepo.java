package com.ss17.repo.productcart;

import com.ss17.entity.Product;
import com.ss17.entity.ProductCart;

import java.util.List;

public interface ProductCartRepo{
    List<ProductCart> findByCustomerId(int customerId);
    void save(ProductCart productCart);
    void delete(ProductCart productCart);
    ProductCart findByCustomerIdAndProductId(int customerId, int productId);
    void deleteCartToEmpty(int customerId);
}
