package com.ss20.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity(name = "seed")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Seed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, unique = true, nullable = false, name = "seed_name")
    private String seedName;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private int stock;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private String image;
}