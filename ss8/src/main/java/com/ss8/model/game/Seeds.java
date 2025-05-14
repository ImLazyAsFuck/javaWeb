package com.ss8.model.game;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seeds{
    private int id;
    private String seedsName;
    private double price;
    private String imageUrl;
}
