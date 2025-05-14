package com.ss8.model.game;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WareHouseSeeds{
    private int id;
    private int quantity;
    private Seeds seeds;
}