package com.example.ss6.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User{
    private int id;
    private String username;
    private String password;
    private String email;
    private String phone;
}
