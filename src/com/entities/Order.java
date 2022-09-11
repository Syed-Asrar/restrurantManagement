package com.entities;

import java.util.UUID;

public class Order {
    private Recipe recipe;
    private double amount;
    private String orderId;

    public Order(Recipe recipe, double amount) {
        this.recipe = recipe;
        this.amount = amount;
        this.orderId = UUID.randomUUID().toString();
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getOrderId() {
        return orderId;
    }
}
