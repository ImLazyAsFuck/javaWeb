package com.example.model;

public class Cart{
    private static int idSequence = 0;
    private int id;
    private Product product;
    private int quantity;

    public Cart(Product product, int quantity) {
        this.id = idSequence++;
        this.product = product;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSubtotal() {
        return product.getPrice() * quantity;
    }
}
