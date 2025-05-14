package com.ss8.model.game;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User{
    private int id;
    private String username;
    private String password;
    private String email;
    private double balance = 10000;
}
