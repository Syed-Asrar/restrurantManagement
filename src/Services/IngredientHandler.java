package Services;

import com.entities.Ingredient;
import exceptions.InsufficentMoneyException;

import java.util.List;
import java.util.Map;

public class IngredientHandler {
    public void viewIngredient(List<Ingredient> ingredientList){
        for(Ingredient ingredient: ingredientList){
            System.out.println(ingredient);
        }
    }
    public boolean isPossibleToOrderIngredient(Ingredient ingredient, double qty, double availbleMoney){
        if(availbleMoney>= ingredient.getRate()*qty)
            return true;
        return false;
    }
    public static void isPossibleToOrderIngredientsList(Map<Ingredient,Double> insufficentIngredients, Double availableMoney){
        double moneyRequired = 0;
        for(Ingredient ingredient: insufficentIngredients.keySet()){
            double qtyToOrder = insufficentIngredients.get(ingredient);
            double rate = ingredient.getRate();
            moneyRequired += qtyToOrder*rate;
        }
        if(moneyRequired>availableMoney){
            throw new InsufficentMoneyException();
        }
    }
}
