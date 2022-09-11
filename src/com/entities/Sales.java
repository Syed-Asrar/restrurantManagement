package com.entities;

public class Sales {
    private double amount;
    private Order order;

    public Sales(double amount, Order order) {
        this.amount = amount;
        this.order = order;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
    @Override
    public String toString(){
        return "Recipe = "+this.getOrder().getRecipe().getName()+" Costs: "+this.getAmount();
    }
}
