package com.example.ss6.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private int id;
    private String name;
    private LocalDate birthday;
    private String phone;
    private String email;
    private double salary;
    private String position;
}
