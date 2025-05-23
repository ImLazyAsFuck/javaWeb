package com.example.model;

public class Product{
    private static int idSequence = 0;
    private int id;
    private String name;
    private double price;

    public Product(String name, double price){
        this.id = ++idSequence;
        this.name = name;
        this.price = price;
    }

    public Product(){
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public double getPrice(){
        return price;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public static void resetCounter() {
        idSequence = 0;
    }
}
