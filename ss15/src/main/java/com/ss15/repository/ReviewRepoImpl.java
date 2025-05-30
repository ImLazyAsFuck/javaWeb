package com.ss15.repository;

import com.ss15.model.B6Product;
import com.ss15.model.Cart;
import com.ss15.model.Orders;
import com.ss15.model.Review;
import com.ss15.repository.ReviewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReviewRepoImpl implements ReviewRepo{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean addReview(Review review) {
        try {
            String sql = "{call saveProductReview(?, ?, ?)}";
            return jdbcTemplate.update(sql, review.getProductId(), review.getRating(), review.getComment()) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<B6Product> getAllProducts() {
        try {
            String sql = "{call getAllProducts()}";
            return jdbcTemplate.query(sql, new B6ProductRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Review> getReviewsByProductId(int productId) {
        try {
            String sql = "{call getAllReviewsByProductId(?)}";
            return jdbcTemplate.query(sql, new ReviewRowMapper(), productId);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public B6Product getProductById(int id) {
        try {
            String sql = "{call getProductById(?)}";
            return jdbcTemplate.queryForObject(sql, new B6ProductRowMapper(), id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean addToCart(Cart cart) {
        try {
            String sql = "{call addToCart(?, ?)}";
            return jdbcTemplate.update(sql, cart.getProductId(), cart.getQuantity()) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Cart> getCartByUserId(int userId) {
        try {
            String sql = "{call getCartByUserId(?)}";
            return jdbcTemplate.query(sql, new CartRowMapper(), userId);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public boolean addOrder(Orders orders) {
        try {
            String sql = "{call addOrder(?, ?, ?, ?)}";
            return jdbcTemplate.update(sql, orders.getUserId(), orders.getRecipientName(), orders.getAddress(), orders.getPhoneNumber()) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static class B6ProductRowMapper implements RowMapper<B6Product> {
        @Override
        public B6Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            B6Product product = new B6Product();
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setPrice(rs.getDouble("price"));
            product.setDescription(rs.getString("description"));
            return product;
        }
    }

    private static class ReviewRowMapper implements RowMapper<Review> {
        @Override
        public Review mapRow(ResultSet rs, int rowNum) throws SQLException {
            Review review = new Review();
            review.setId(rs.getInt("id"));
            review.setProductId(rs.getInt("product_id"));
            review.setUserId(rs.getInt("user_id"));
            review.setRating(rs.getInt("rating"));
            review.setComment(rs.getString("comment"));
            return review;
        }
    }

    private static class CartRowMapper implements RowMapper<Cart> {
        @Override
        public Cart mapRow(ResultSet rs, int rowNum) throws SQLException {
            Cart cart = new Cart();
            cart.setId(rs.getInt("id"));
            cart.setUserId(rs.getInt("user_id"));
            cart.setProductId(rs.getInt("product_id"));
            cart.setQuantity(rs.getInt("quantity"));
            return cart;
        }
    }
}