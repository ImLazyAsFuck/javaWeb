package com.example.service;

import com.example.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class UserManager{
    private final static List<User> users = new ArrayList<>();
    
    public static void addUser(User user){
        users.add(user);
    }
    
    public static List<User> getUsers(){
        return users;
    }
    
    public static void removeUser(int id){
        if (users.isEmpty()) {
            return;
        }

        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getId() == id) {
                iterator.remove();
                break;
            }
        }
    }
}
