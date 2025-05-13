package com.example.ss6.dao.userdao;

import com.example.ss6.model.User;
import java.util.List;

public interface UserDAO {
    List<User> findAll();
    User findByUsername(String username);
    User findByEmail(String email);
    boolean save(User user);
    boolean updateStatus(int id, boolean status);
}
