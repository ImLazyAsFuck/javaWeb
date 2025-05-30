package com.ss15.service;

import com.ss15.model.B6Product;
import com.ss15.model.Cart;
import com.ss15.model.Orders;
import com.ss15.model.Review;

import java.util.List;

public interface ReviewService{
    boolean addReview(Review review);
    List<B6Product> getAllProducts();
    List<Review> getReviewsByProductId(int productId);
    B6Product getProductById(int id);
    boolean addToCart(Cart cart);
    List<Cart> getCartByUserId(int userId);
    boolean addOrder(Orders orders);
}
