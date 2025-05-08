package com.example.service;

import com.example.model.User;

import java.util.ArrayList;
import java.util.List;

public class BookManager{
    private final static List<User> users = new ArrayList<>();
    public static void addUser(User user){
        users.add(user);
    }
    public static List<User> getUsers(){
        return users;
    }
    public static void removeUser(int id){
        User user = users.get(id);
        users.remove(user);
    }
//    public
}
