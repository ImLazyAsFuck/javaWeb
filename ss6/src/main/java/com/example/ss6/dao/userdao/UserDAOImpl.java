package com.example.ss6.dao.userdao;

import com.example.ss6.model.User;
import com.example.ss6.utils.DBConnect;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public List<User> findAll() {
        Connection con = null;
        CallableStatement cs = null;
        List<User> users = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            cs = con.prepareCall("{call find_all_users()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                users.add(user);
            }
        } catch (Exception e) {
            e.fillInStackTrace();
        } finally {
            DBConnect.closeConnection(con, cs);
        }
        return users;
    }

    @Override
    public User findByUsername(String username) {
        Connection con = null;
        CallableStatement cs = null;
        User user = null;
        try {
            con = DBConnect.getConnection();
            cs = con.prepareCall("{call find_user_by_username(?)}");
            cs.setString(1, username);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
            }
        } catch (Exception e) {
            e.fillInStackTrace();
        } finally {
            DBConnect.closeConnection(con, cs);
        }
        return user;
    }

    @Override
    public User findByEmail(String email) {
        Connection con = null;
        CallableStatement cs = null;
        User user = null;
        try {
            con = DBConnect.getConnection();
            cs = con.prepareCall("{call find_user_by_email(?)}");
            cs.setString(1, email);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
            }
        } catch (Exception e) {
            e.fillInStackTrace();
        } finally {
            DBConnect.closeConnection(con, cs);
        }
        return user;
    }

    @Override
    public boolean save(User user) {
        Connection con = null;
        CallableStatement cs = null;
        try {
            con = DBConnect.getConnection();
            cs = con.prepareCall("{call add_user(?, ?, ?, ?)}");
            cs.setString(1, user.getUsername());
            cs.setString(2, user.getPassword());
            cs.setString(3, user.getEmail());
            cs.setString(4, user.getPhone());
            return cs.executeUpdate() > 0;
        } catch (Exception e) {
            e.fillInStackTrace();
        } finally {
            DBConnect.closeConnection(con, cs);
        }
        return false;
    }

    @Override
    public boolean updateStatus(int id, boolean status) {
        Connection con = null;
        CallableStatement cs = null;
        try {
            con = DBConnect.getConnection();
            cs = con.prepareCall("{call update_user_status(?, ?)}");
            cs.setInt(1, id);
            cs.setBoolean(2, status);
            return cs.executeUpdate() > 0;
        } catch (Exception e) {
            e.fillInStackTrace();
        } finally {
            DBConnect.closeConnection(con, cs);
        }
        return false;
    }
}
