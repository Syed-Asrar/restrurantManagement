package io;

import com.entities.Ingredient;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class IngredientReader {
    public List<Ingredient> readIngredients(String filePath) throws FileNotFoundException {
        List<String> lines = CustomFileReader.readFile(filePath);
        List<Ingredient> ingredientList = new ArrayList<>();
        for(String line: lines){
            String[] data =line.split(" ");
            ingredientList.add(new Ingredient(data[0],Double.parseDouble(data[1]),Double.parseDouble(data[2])));
        }
        System.out.println("Read "+ingredientList.size()+" ingredients from the file!");
        return ingredientList;
    }
}
