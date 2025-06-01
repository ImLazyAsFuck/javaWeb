package com.ss16.service.auth;

import com.ss16.model.user.User;
import com.ss16.repository.auth.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{
    @Autowired
    private AuthRepository authRepository;

    @Override
    public boolean existsByUsername(String username){
        return authRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email){
        return authRepository.existsByEmail(email);
    }

    @Override
    public void save(User user){
        authRepository.save(user);
    }

    @Override
    public User get(String username, String password){
        return authRepository.get(username, password);
    }
}
