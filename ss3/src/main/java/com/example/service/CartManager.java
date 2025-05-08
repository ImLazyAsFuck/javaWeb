package com.example.service;

import com.example.model.Cart;
import com.example.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Optional;

public class CartManager {
    private final static List<Cart> carts = new ArrayList<>();

    public static void addProduct(Product product) {
            Cart cart = new Cart(product, 1);
            carts.add(cart);
    }

    public static List<Cart> getCart() {
        return carts;
    }

    public static void removeProduct(int id) {
        if (carts.isEmpty()) {
            return;
        }
        Iterator<Cart> iterator = carts.iterator();
        while (iterator.hasNext()) {
            Cart cart = iterator.next();
            if (cart.getProduct().getId() == id) {
                iterator.remove();
                break;
            }
        }
    }

    public static double getTotalPrice() {
        return carts.stream()
                .mapToDouble(Cart::getSubtotal)
                .sum();
    }

    public static void updateQuantity(int productId, int newQuantity) {
        carts.stream()
                .filter(c -> c.getProduct().getId() == productId)
                .findFirst()
                .ifPresent(cart -> {
                    if (newQuantity > 0) {
                        cart.setQuantity(newQuantity);
                    } else {
                        removeProduct(productId);
                    }
                });
    }

    public static void clearCart() {
        carts.clear();
    }
}