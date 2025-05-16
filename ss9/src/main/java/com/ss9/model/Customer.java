package com.ss9.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer{
    private Long id;
    private String username;
    private String password;
    private String phone;
    private String address;
    private Gender gender;
    private String email;
}
