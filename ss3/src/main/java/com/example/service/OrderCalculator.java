package com.example.service;

import java.util.List;

public class OrderCalculator {
    public double calculateTotal(List<Integer> quantities, List<Double> prices) {
        double total = 0.0;
        for (int i = 0; i < quantities.size(); i++) {
            total += quantities.get(i) * prices.get(i);
        }
        return total;
    }
}
