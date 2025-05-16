package com.ss9.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie{
    private Long id;
    private String title;
    private String director;
    private String genre;
    private String language;
    private String description;
    private Integer duration;
}
