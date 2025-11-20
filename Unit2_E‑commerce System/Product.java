package com.ecommerce;

import java.util.Objects;

public class Product {
    private final String productID;
    private final String name;
    private double price;
    private int stock;

    public Product(String productID, String name, double price, int stock) {
        if (price < 0 || stock < 0) throw new IllegalArgumentException("Invalid price or stock");
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getProductID() { return productID; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }

    public void setPrice(double price) {
        if (price < 0) throw new IllegalArgumentException("Price cannot be negative");
        this.price = price;
    }

    public void reduceStock(int qty) {
        if (qty < 0) throw new IllegalArgumentException("Invalid quantity");
        if (qty > stock) throw new IllegalStateException("Insufficient stock for " + name);
        stock -= qty;
    }

    @Override
    public String toString() {
        return String.format("%s - %s : $%.2f (in stock: %d)", productID, name, price, stock);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product p = (Product) o;
        return Objects.equals(productID, p.productID);
    }

    @Override
    public int hashCode() { return Objects.hash(productID); }
}
