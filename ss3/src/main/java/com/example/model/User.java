package com.example.model;

public class User{
    private static int idSequence = 0;
    private int id;
    private String name;
    private String email;

    public User(String name, String email){
        this.id = ++idSequence;
        this.name = name;
        this.email = email;
    }
}
