package com.ss14.service;

import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{
    @Override
    public boolean authenticate(String username, String password){
        return "admin".equals(username) && "password123".equals(password);
    }
}
