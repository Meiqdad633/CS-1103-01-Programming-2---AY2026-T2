package com.ecommerce;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Customer {
    private final String customerID;
    private final String name;
    private final Map<Product, Integer> cart = new LinkedHashMap<>();

    public Customer(String customerID, String name) {
        this.customerID = customerID;
        this.name = name;
    }

    public String getCustomerID() { return customerID; }
    public String getName() { return name; }

    public void addToCart(Product p, int qty) {
        if (qty <= 0) throw new IllegalArgumentException("Quantity must be positive");
        int current = cart.getOrDefault(p, 0);
        cart.put(p, current + qty);
    }

    public void removeFromCart(Product p) { cart.remove(p); }

    public double cartTotal() {
        return cart.entrySet().stream()
                .mapToDouble(e -> e.getKey().getPrice() * e.getValue())
                .sum();
    }

    public Map<Product, Integer> getCartItems() { return Collections.unmodifiableMap(cart); }

    public void clearCart() { cart.clear(); }

    public int uniqueItemCount() { return cart.size(); }
}
