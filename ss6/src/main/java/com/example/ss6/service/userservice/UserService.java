package com.example.ss6.service.userservice;

import com.example.ss6.model.User;
import java.util.List;

public interface UserService {
    List<User> findAll();
    User findByUsername(String username);
    User findByEmail(String email);
    boolean save(User user);
    boolean lockUser(int id);
    boolean unlockUser(int id);
    boolean authenticate(String username, String password);
}
