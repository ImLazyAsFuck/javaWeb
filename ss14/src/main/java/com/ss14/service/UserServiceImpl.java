package com.ss14.service;

import com.ss14.model.User;
import com.ss14.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepo;

    @Override
    public void save(User user){
        userRepo.save(user);
    }
}
