package Services;

import com.entities.Expense;
import com.entities.Sales;

import java.sql.SQLOutput;
import java.util.List;

public class AccountHandler {
    public void printSales(List<Sales> salesList){
        System.out.println("Total number sales so far: "+ salesList.size());
        for(Sales sale: salesList) {
            System.out.println(sale.toString());
        }
    }
    public void printExpenses(List<Expense> expenseList){
        System.out.println("Total number Expenses so far: "+ expenseList.size());
        for(Expense expense: expenseList){
            System.out.println(expense.toString());
        }
    }
    public void printProfit(List<Sales> salesList, List<Expense> expenseList){
        double profit = 0;
        for(Sales sales: salesList){
            profit += sales.getAmount();
        }
        for(Expense expense: expenseList){
            profit -= expense.getAmount();
        }
        System.out.println("Net profit: "+ profit);
    }
}
