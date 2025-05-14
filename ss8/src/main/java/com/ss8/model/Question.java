package com.ss8.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question{
    private int id;
    private String imageUrl;
    private String answer;
}
