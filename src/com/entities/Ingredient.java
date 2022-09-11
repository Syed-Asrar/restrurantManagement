package com.entities;

public class Ingredient {
    private String name;
    private double quantity;
    private double rate;

    public Ingredient(String name, double quantity, double rate) {
        this.name = name;
        this.quantity = quantity;
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
    @Override
    public String toString() {
        return "Ingredient: "+this.getName()+", Available Quantity: "+this.getQuantity()+", Rate per unit: "+this.getRate();
    }

    @Override
    public boolean equals(Object object){
        if(this.getClass()!=object.getClass()){
            return false;
        }
        Ingredient newIngredient = (Ingredient) object;
        return this.getName().equals(newIngredient.getName());
    }
}
