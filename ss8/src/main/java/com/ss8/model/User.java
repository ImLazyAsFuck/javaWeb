package com.ss8.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User{
    private int id;
    private String name;
    private String email;
    private String phone;
    private LocalDate birthday;
}
