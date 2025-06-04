package com.ss17.service.productcart;

import com.ss17.entity.ProductCart;

import java.util.List;

public interface ProductCartService{
    List<ProductCart> findByCustomerId(int customerId);
    void save(ProductCart productCart);
    void delete(ProductCart productCart);
    ProductCart findByCustomerIdAndProductId(int customerId, int productId);
    void deleteCartToEmpty(int customerId);
}
