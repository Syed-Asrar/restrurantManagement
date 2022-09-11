package com.entities;

import java.util.Map;

public class Recipe {
    private String name;
    private Map<Ingredient,Double> composition;
    private double amount;

    public Recipe(String name, Map<Ingredient, Double> composition, double amount) {
        this.name = name;
        this.composition = composition;
        this.amount = amount;
    }
    @Override
    public String toString(){
        return this.getName()+"\n"+this.getComposition().toString()+"\n"+this.getAmount();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Ingredient, Double> getComposition() {
        return composition;
    }

    public void setComposition(Map<Ingredient, Double> composition) {
        this.composition = composition;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
