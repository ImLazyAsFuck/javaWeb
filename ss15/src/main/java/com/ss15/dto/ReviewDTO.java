package com.ss15.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO{
    private long id;
    private long productId;
    private long userId;
    @Min(1) @Max(5)
    private int rating;
    private String comment;
}
