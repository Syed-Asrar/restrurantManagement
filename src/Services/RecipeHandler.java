package Services;

import com.entities.Ingredient;
import com.entities.Recipe;
import exceptions.InsufficentIngredientsException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecipeHandler {
    public void isPossibleToPrepareRecipe(Recipe recipe, List<Ingredient> ingredientList){
        Map<Ingredient,Double> composition = recipe.getComposition();
        Map<Ingredient,Double> insufficentIngredientMap = new HashMap<>();
        for(Ingredient ingredient: ingredientList){
            if(composition.containsKey(ingredient)){
                double qtyUsed = composition.get(ingredient);
                double availableQty = ingredient.getQuantity();
                if(qtyUsed>availableQty){
                    insufficentIngredientMap.put(ingredient,qtyUsed-ingredient.getQuantity());
                }
            }
        }
        if(insufficentIngredientMap.size()>0) {
            throw new InsufficentIngredientsException(insufficentIngredientMap);
        }
    }
}
