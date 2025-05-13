package com.example.ss6.service.userservice;

import com.example.ss6.dao.userdao.UserDAO;
import com.example.ss6.dao.userdao.UserDAOImpl;
import com.example.ss6.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDAO USER_DAO = new UserDAOImpl();

    @Override
    public List<User> findAll() {
        return USER_DAO.findAll();
    }

    @Override
    public User findByUsername(String username) {
        return USER_DAO.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return USER_DAO.findByEmail(email);
    }

    @Override
    public boolean save(User user) {
        if (findByUsername(user.getUsername()) != null) {
            return false;
        }
        if (findByEmail(user.getEmail()) != null) {
            return false;
        }
        return USER_DAO.save(user);
    }

    @Override
    public boolean lockUser(int id) {
        return USER_DAO.updateStatus(id, false);
    }

    @Override
    public boolean unlockUser(int id) {
        return USER_DAO.updateStatus(id, true);
    }

    @Override
    public boolean authenticate(String username, String password) {
        User user = findByUsername(username);
        if (user == null) {
            return false;
        }
        return user.getPassword().equals(password);
    }
}
