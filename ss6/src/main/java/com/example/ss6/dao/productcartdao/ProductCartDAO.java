package com.example.ss6.dao.productcartdao;

import com.example.ss6.model.ProductCart;
import java.util.List;

public interface ProductCartDAO {
    List<ProductCart> findBySessionId(String sessionId);
    ProductCart findCartItem(String sessionId, int productId);
    boolean addToCart(ProductCart productCart);
    boolean updateQuantity(int id, int quantity);
    boolean removeFromCart(int id);
    boolean clearCart(String sessionId);
}
