package com.example.ss6.service.productcartservice;

import com.example.ss6.dao.productcartdao.ProductCartDAO;
import com.example.ss6.dao.productcartdao.ProductCartDAOImpl;
import com.example.ss6.model.ProductCart;

import java.util.List;

public class ProductCartServiceImpl implements ProductCartService {
    private final ProductCartDAO PRODUCT_CART_DAO = new ProductCartDAOImpl();

    @Override
    public List<ProductCart> findBySessionId(String sessionId) {
        return PRODUCT_CART_DAO.findBySessionId(sessionId);
    }

    @Override
    public boolean addToCart(String sessionId, int productId, int quantity) {
        ProductCart existingItem = PRODUCT_CART_DAO.findCartItem(sessionId, productId);

        if (existingItem != null) {
            int newQuantity = existingItem.getQuantity() + quantity;
            return PRODUCT_CART_DAO.updateQuantity(existingItem.getId(), newQuantity);
        } else {
            ProductCart cartItem = new ProductCart();
            cartItem.setSessionId(sessionId);
            cartItem.setProductId(productId);
            cartItem.setQuantity(quantity);
            return PRODUCT_CART_DAO.addToCart(cartItem);
        }
    }

    @Override
    public boolean updateQuantity(int cartItemId, int quantity) {
        return PRODUCT_CART_DAO.updateQuantity(cartItemId, quantity);
    }

    @Override
    public boolean removeFromCart(int cartItemId) {
        return PRODUCT_CART_DAO.removeFromCart(cartItemId);
    }

    @Override
    public boolean clearCart(String sessionId) {
        return PRODUCT_CART_DAO.clearCart(sessionId);
    }

    @Override
    public double getCartTotal(String sessionId) {
        List<ProductCart> cartItems = findBySessionId(sessionId);
        return cartItems.stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
    }
}