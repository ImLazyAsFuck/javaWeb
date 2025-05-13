package com.example.ss6.service.productcartservice;

import com.example.ss6.model.ProductCart;
import java.util.List;

public interface ProductCartService {
    List<ProductCart> findBySessionId(String sessionId);
    boolean addToCart(String sessionId, int productId, int quantity);
    boolean updateQuantity(int cartItemId, int quantity);
    boolean removeFromCart(int cartItemId);
    boolean clearCart(String sessionId);
    double getCartTotal(String sessionId);
}
