package com.ss16.repository.auth;

import com.ss16.model.user.User;

public interface AuthRepository{
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    void save(User user);
    User get(String username, String password);
}
