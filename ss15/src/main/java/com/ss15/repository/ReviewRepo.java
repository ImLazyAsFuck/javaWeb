package com.ss15.repository;

import com.ss15.model.*;

import java.util.List;

public interface ReviewRepo{
    boolean addReview(Review review);
    List<B6Product> getAllProducts();
    List<Review> getReviewsByProductId(int productId);
    B6Product getProductById(int id);
    boolean addToCart(Cart cart);
    List<Cart> getCartByUserId(int userId);
    boolean addOrder(Orders orders);
}
