package com.example.ss5.model;

import lombok.Data;

@Data
public class Student{
    private static int idSequence = 0;
    private int id;
    private String name;
    private int age;
    private String address;

    public Student(){
        this.id = ++idSequence;
    }

    public Student(String name, int age, String address){
        this.id = ++idSequence;
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setAge(int age){
        this.age = age;
    }

    public void setAddress(String address){
        this.address = address;
    }
}
