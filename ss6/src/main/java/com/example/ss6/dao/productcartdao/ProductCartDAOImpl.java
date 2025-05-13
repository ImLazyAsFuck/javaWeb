package com.example.ss6.dao.productcartdao;

import com.example.ss6.model.Product;
import com.example.ss6.model.ProductCart;
import com.example.ss6.utils.DBConnect;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductCartDAOImpl implements ProductCartDAO {
    @Override
    public List<ProductCart> findBySessionId(String sessionId) {
        Connection con = null;
        CallableStatement cs = null;
        List<ProductCart> cartItems = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            cs = con.prepareCall("{call find_cart_by_session_id(?)}");
            cs.setString(1, sessionId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                ProductCart cartItem = new ProductCart();
                cartItem.setId(rs.getInt("id"));
                cartItem.setSessionId(rs.getString("session_id"));
                cartItem.setProductId(rs.getInt("product_id"));
                cartItem.setQuantity(rs.getInt("quantity"));
                
                // Set product details
                Product product = new Product();
                product.setId(rs.getInt("product_id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setImageUrl(rs.getString("image_url"));
                cartItem.setProduct(product);
                
                cartItems.add(cartItem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeConnection(con, cs);
        }
        return cartItems;
    }

    @Override
    public ProductCart findCartItem(String sessionId, int productId) {
        Connection con = null;
        CallableStatement cs = null;
        ProductCart cartItem = null;
        try {
            con = DBConnect.getConnection();
            cs = con.prepareCall("{call find_cart_item(?, ?)}");
            cs.setString(1, sessionId);
            cs.setInt(2, productId);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                cartItem = new ProductCart();
                cartItem.setId(rs.getInt("id"));
                cartItem.setSessionId(rs.getString("session_id"));
                cartItem.setProductId(rs.getInt("product_id"));
                cartItem.setQuantity(rs.getInt("quantity"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeConnection(con, cs);
        }
        return cartItem;
    }

    @Override
    public boolean addToCart(ProductCart productCart) {
        Connection con = null;
        CallableStatement cs = null;
        try {
            con = DBConnect.getConnection();
            cs = con.prepareCall("{call add_to_cart(?, ?, ?)}");
            cs.setString(1, productCart.getSessionId());
            cs.setInt(2, productCart.getProductId());
            cs.setInt(3, productCart.getQuantity());
            return cs.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeConnection(con, cs);
        }
        return false;
    }

    @Override
    public boolean updateQuantity(int id, int quantity) {
        Connection con = null;
        CallableStatement cs = null;
        try {
            con = DBConnect.getConnection();
            cs = con.prepareCall("{call update_cart_quantity(?, ?)}");
            cs.setInt(1, id);
            cs.setInt(2, quantity);
            return cs.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeConnection(con, cs);
        }
        return false;
    }

    @Override
    public boolean removeFromCart(int id) {
        Connection con = null;
        CallableStatement cs = null;
        try {
            con = DBConnect.getConnection();
            cs = con.prepareCall("{call remove_from_cart(?)}");
            cs.setInt(1, id);
            return cs.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeConnection(con, cs);
        }
        return false;
    }

    @Override
    public boolean clearCart(String sessionId) {
        Connection con = null;
        CallableStatement cs = null;
        try {
            con = DBConnect.getConnection();
            cs = con.prepareCall("{call clear_cart(?)}");
            cs.setString(1, sessionId);
            return cs.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnect.closeConnection(con, cs);
        }
        return false;
    }
}
