package com.ecommerce.orders;

import com.ecommerce.Customer;
import com.ecommerce.Product;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

public class Order {
    public enum Status { PENDING, PLACED, CANCELLED }

    private final String orderID;
    private final Customer customer;
    private final Map<Product, Integer> items;
    private double total;
    private Status status;

    public Order(Customer customer, Map<Product, Integer> items) {
        this.orderID = UUID.randomUUID().toString().substring(0,8);
        this.customer = customer;
        this.items = new LinkedHashMap<>(items);
        this.status = Status.PENDING;
        calculateTotal();
    }

    private void calculateTotal() {
        total = items.entrySet().stream()
                .mapToDouble(e -> e.getKey().getPrice() * e.getValue())
                .sum();
    }

    public String getOrderID() { return orderID; }
    public double getTotal() { return total; }
    public Status getStatus() { return status; }

    public void placeOrder() {
        for (Map.Entry<Product,Integer> e : items.entrySet()) {
            Product p = e.getKey();
            int qty = e.getValue();
            if (qty > p.getStock()) throw new IllegalStateException("Product " + p.getName() + " has insufficient stock");
        }
        for (Map.Entry<Product,Integer> e : items.entrySet()) {
            e.getKey().reduceStock(e.getValue());
        }
        status = Status.PLACED;
    }

    public String summary() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order ").append(orderID).append(" | Customer: ").append(customer.getName()).append("\n");
        items.forEach((p,q) -> sb.append(String.format("- %s x%d => $%.2f\n", p.getName(), q, p.getPrice()*q)));
        sb.append(String.format("Total: $%.2f | Status: %s\n", total, status));
        return sb.toString();
    }
}
