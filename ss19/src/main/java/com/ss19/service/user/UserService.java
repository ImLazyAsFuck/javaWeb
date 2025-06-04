package com.ss19.service.user;

import com.ss19.entity.User;

import java.util.List;

public interface UserService{
    List<User> findAll();
    List<User> findAll(int page, int size);
    void changeStatus(Long id, boolean status);
    long count();
}
