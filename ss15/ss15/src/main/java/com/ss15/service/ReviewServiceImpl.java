package com.ss15.service;

import com.ss15.model.B6Product;
import com.ss15.model.Cart;
import com.ss15.model.Orders;
import com.ss15.model.Review;
import com.ss15.repository.ReviewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService{
    @Autowired
    private ReviewRepo reviewRepo;


    @Override
    public boolean addReview(Review review){
        return reviewRepo.addReview(review);
    }

    @Override
    public List<B6Product> getAllProducts(){
        return reviewRepo.getAllProducts();
    }

    @Override
    public List<Review> getReviewsByProductId(int productId){
        return reviewRepo.getReviewsByProductId(productId);
    }

    @Override
    public B6Product getProductById(int id){
        return reviewRepo.getProductById(id);
    }

    @Override
    public boolean addToCart(Cart cart){
        return reviewRepo.addToCart(cart);
    }

    @Override
    public List<Cart> getCartByUserId(int userId){
        return reviewRepo.getCartByUserId(userId);
    }

    @Override
    public boolean addOrder(Orders orders){
        return reviewRepo.addOrder(orders);
    }
}
