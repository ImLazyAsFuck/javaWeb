package com.b10.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cv{
    private long id;
    private String fullname;
    private String email;
    private String phone;
    private String education;
    private String experience;
    private String skills;
    private String image;
}
