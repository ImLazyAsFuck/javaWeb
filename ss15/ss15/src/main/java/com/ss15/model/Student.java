package com.ss15.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student{
    private int id;
    private String name;
    private int age;
    private String email;
    private String grade;
    private String phone;
}
