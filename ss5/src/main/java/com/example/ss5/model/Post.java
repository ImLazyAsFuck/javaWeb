package com.example.ss5.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    private int id;
    private String title;
    private String content;
    private String author;
    private String publishDate;
}
