package com.entities;

public class Expense {
    private double Amount;
    private PurchaseOrder purchaseOrder;
    private ExpenseType expenseType;

    public Expense(double totalAmount, PurchaseOrder purchaseOrder, ExpenseType expenseType) {
        this.Amount = totalAmount;
        this.purchaseOrder = purchaseOrder;
        this.expenseType = expenseType;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double totalAmount) {
        this.Amount = totalAmount;
    }

    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public ExpenseType getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(ExpenseType expenseType) {
        this.expenseType = expenseType;
    }
    @Override
    public String toString(){
        return "Amount: "+this.getAmount()+" Expensettype: "+this.getExpenseType()+" of "+this.getPurchaseOrder().getComposition().keySet().toString();
    }
}
