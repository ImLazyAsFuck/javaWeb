package com.example.service;

import com.example.model.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductFile{
    private static final String FILE_NAME = "products.txt";
    public static List<Product> productList = new ArrayList<>();

    public static void loadProductsFromFile() {
        File file = new File(FILE_NAME);
        System.out.println("Loading products from: " + file.getAbsolutePath());
        if (!file.exists()) {
            System.out.println("File does not exist, initializing empty product list.");
            productList = new ArrayList<>();
            return;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = ois.readObject();
            if (obj instanceof List) {
                productList = (List<Product>) obj;
            } else {
                System.err.println("Invalid data in file, initializing empty product list.");
                productList = new ArrayList<>();
            }
        } catch (Exception e) {
            System.err.println("Error loading products: " + e.getMessage());
            productList = new ArrayList<>();
        }
    }

    public static void saveProductsToFile(){
        File file = new File(FILE_NAME);
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))){
            List<Product> listToSave = productList != null ? productList : new ArrayList<Product>();
            oos.writeObject(listToSave);
            System.out.println("Products saved successfully to: " + file.getAbsolutePath());
            System.out.println("Saved " + listToSave.size() + " products");
        }catch(IOException e){
            System.err.println("Error saving products: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
