package io;

import com.entities.Ingredient;
import com.entities.Recipe;
import exceptions.InvalidIngredientException;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecipeReader {
    public List<Recipe> readRecipe(String filePath, List<Ingredient> ingredientList) throws FileNotFoundException, InvalidIngredientException {
        List<String> lines = CustomFileReader.readFile(filePath);
        List<Recipe> recipeList = new ArrayList<>();
        for(String line: lines){
            Map<Ingredient,Double> composition = new HashMap<>();
            String[] data = line.split(" ");
            String recipeName = data[0];
            double recipeRate = Double.parseDouble(data[1]);
            for(int i = 2; i< data.length; i+=2){
                String ingredientName = data[i];
                double qty = Double.parseDouble(data[i+1]);
                boolean flag= false;
                for(int j=0; j<ingredientList.size();j++){
                    if(ingredientList.get(j).getName().equals(ingredientName)) {
                        composition.put(ingredientList.get(j),qty);
                        flag = true;
                        break;
                    }
                }
                if(flag==false)
                    throw new InvalidIngredientException("Ingredient "+ingredientName+" not found!");
            }
            Recipe recipe = new Recipe(recipeName,composition,recipeRate);
            recipeList.add(recipe);
        }
        System.out.println("Read "+recipeList.size()+" recipes from file!");

        return recipeList;
    }
}
