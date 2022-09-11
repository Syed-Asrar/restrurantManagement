package exceptions;

import com.entities.Ingredient;

import java.util.Map;

public class InsufficentIngredientsException extends RuntimeException{
    private Map<Ingredient, Double> insufficentIngredientMap;
    public InsufficentIngredientsException(Map<Ingredient,Double> insufficentIngredientMap){
        this.insufficentIngredientMap = insufficentIngredientMap;
    }
    public Map<Ingredient,Double> getMap(){
        return this.insufficentIngredientMap;
    }
}
