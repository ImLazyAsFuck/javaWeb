package com.example.ss5.model;

import lombok.Data;

import java.util.Date;

@Data
public class Task{
    private int id;
    private String name;
    private boolean completed;
    private Date dueDate;
}
