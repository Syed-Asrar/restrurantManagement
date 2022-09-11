import Services.AccountHandler;
import Services.IngredientHandler;
import Services.RecipeHandler;
import com.entities.*;
import exceptions.*;
import io.AccountsReader;
import io.IngredientReader;
import io.RecipeReader;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    private static List<Sales> salesList;
    private static List<Expense> expenseList;
    private static List<Ingredient> ingredientList;
    private static List<Recipe> recipeList;
    private static double availableMoney;
    private static AccountHandler accountHandler;
    private static IngredientHandler ingredientHandler;
    private static RecipeHandler recipeHandler;
    private static IngredientReader ingredientReader;
    private static RecipeReader recipeReader;
    private static AccountsReader accountsReader;
    public static void main(String args[]) throws FileNotFoundException, InvalidIngredientException {
        ingredientHandler = new IngredientHandler();
        ingredientReader = new IngredientReader();
        recipeReader = new RecipeReader();
        accountsReader= new AccountsReader();
        salesList = new ArrayList<>();
        expenseList = new ArrayList<>();
        accountHandler = new AccountHandler();
        recipeHandler = new RecipeHandler();
        availableMoney = accountsReader.balance("resources/accounts.txt");
        ingredientList = ingredientReader.readIngredients("resources/ingredientList.txt");
        recipeList = recipeReader.readRecipe("resources/recipe.txt", ingredientList);
        Scanner sc = new Scanner(System.in);
        double selectedQuantity = 0;
        Ingredient selectedIngredient = null;
        Map<Ingredient,Double> insufficentIngredientMap = new HashMap<>();
        Recipe recipeOrdered = null;
        CommandType currCommand = CommandType.NO_COMMAND;
        while(true){
                try {
                    if (currCommand == CommandType.NO_COMMAND) {
                        int choice = displayPrompt();
                        if(choice>0 && choice<8)
                            currCommand = CommandType.values()[choice];
                        else{
                            System.out.println("Invalid Command. Try Again!");
                            currCommand = CommandType.NO_COMMAND;
                        }
                    } else if (currCommand == CommandType.VIEW_AVAILABLE_INGREDIENTS) {
                        ingredientHandler.viewIngredient(ingredientList);
                        currCommand = CommandType.NO_COMMAND;
                    } else if (currCommand == CommandType.ORDER_SPECIFIC_INGREDIENT) {
                        selectedIngredient = selectIngredient();
                        currCommand = CommandType.INPUT_INGREDIENT_QTY;
                    }
                    else if (currCommand == CommandType.INPUT_INGREDIENT_QTY) {
                        selectedQuantity = ingredientQty();
                        if (ingredientHandler.isPossibleToOrderIngredient(selectedIngredient, selectedQuantity, availableMoney)) {
                            purchaseIngredient(selectedIngredient, selectedQuantity);
                            System.out.println(selectedIngredient+" Ordered successfully!");
                            currCommand = CommandType.NO_COMMAND;
                        } else {
                            throw new InsufficentMoneyException();
                        }
                    }else if (currCommand == CommandType.VIEW_TOTAL_SALES) {
                        accountHandler.printSales(salesList);
                        currCommand = CommandType.NO_COMMAND;
                    } else if (currCommand == CommandType.VIEW_TOTAL_EXPENSES) {
                        accountHandler.printExpenses(expenseList);
                        currCommand = CommandType.NO_COMMAND;
                    }
                    else if(currCommand == CommandType.PLACE_ORDER){
                        recipeOrdered = selectRecipe();
                        recipeHandler.isPossibleToPrepareRecipe(recipeOrdered,ingredientList);
                        currCommand = CommandType.FINALIZE_ORDER;
                    }
                    else if (currCommand == CommandType.VIEW_NET_PROFIT) {
                        currCommand = CommandType.NO_COMMAND;
                        accountHandler.printProfit(salesList, expenseList);
                    } else if (currCommand == CommandType.ORDER_MULTIPLE_INGREDIENTS) {
                            ingredientHandler.isPossibleToOrderIngredientsList(insufficentIngredientMap, availableMoney);
                            purchaseMultipleIngredients(insufficentIngredientMap);
                            currCommand = CommandType.FINALIZE_ORDER;
                    } else if (currCommand == CommandType.FINALIZE_ORDER) {
                            finalizeOrder(recipeOrdered);
                            System.out.println("Sucessfully Ordered "+recipeOrdered.getName());
                            currCommand = CommandType.NO_COMMAND;
                    }
                    if (currCommand == CommandType.EXIT)
                        System.exit(0);
                }
                catch(IngredientNotFoundException ex){
                    System.out.println(ex.getMessage()+"\nPress 1 to go to main menu\nPress 2 to order other ingredient");
                    if(sc.nextInt()==1)
                        currCommand = CommandType.NO_COMMAND;
                    else
                        currCommand = CommandType.ORDER_SPECIFIC_INGREDIENT;

                }
                catch(RecipeNotFoundException ex){
                    System.out.println(ex.getMessage());
                    System.out.println("Press 1 to order other recipe.");
                    System.out.println("Press 2 to go to main menu.");
                    if(sc.nextInt()==1)
                        currCommand = CommandType.PLACE_ORDER;
                    else
                        currCommand = CommandType.NO_COMMAND;
                }
                catch(InsufficentIngredientsException ex){
                    insufficentIngredientMap = ex.getMap();
                    System.out.println(("Insufficent Ingredients!\nPress 1 to order ingredients"));
                    System.out.println("Press 2 to go to main menu.");
                    if(sc.nextInt()==1)
                        currCommand = CommandType.ORDER_MULTIPLE_INGREDIENTS;
                    else
                        currCommand = CommandType.NO_COMMAND;
                }
                catch(InsufficentMoneyException ex){
                    System.out.println("Insufficent money");
                    currCommand = CommandType.NO_COMMAND;
                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                    currCommand = CommandType.NO_COMMAND;
                }
        }
    }
    public static int displayPrompt(){
        System.out.println("Available Money is: "+ availableMoney);
        System.out.println("Select one of the options:");
        System.out.println("1. View available ingredients.");
        System.out.println("2. Order specific ingredient.");
        System.out.println("3. View total sales.");
        System.out.println("4. View total expenses.");
        System.out.println("5. View net profit.");
        System.out.println("6. Place order.");
        System.out.println("7. Exit program.");
        System.out.println("Enter your command number ");
        Scanner sc = new Scanner(System.in);
        try {
            int choice = sc.nextInt();
            return choice;
        }
        catch(Exception ex){
            return 0;
        }
    }
    public static Ingredient selectIngredient(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter ingredient name you want to order");
        String ingredientName = sc.nextLine();
        for(int i = 0; i< ingredientList.size(); i++){
            if(ingredientList.get(i).getName().toLowerCase().equals(ingredientName.toLowerCase()))
                return ingredientList.get(i);
        }
        throw new IngredientNotFoundException("Ingredient "+ ingredientName +" not found!");
    }
    public static double ingredientQty(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Input ingredient quantity");
        double qty = sc.nextDouble();
        return qty;
    }
    public static void purchaseIngredient(Ingredient ingredientOrdered, double qtyOrdered){
        for(int i=0; i<ingredientList.size(); i++){
            if(ingredientList.get(i).getName().equals((ingredientOrdered.getName()))) {
                double oldQty = ingredientList.get(i).getQuantity();
                ingredientList.get(i).setQuantity(oldQty + qtyOrdered);
            }
        }
        Map<Ingredient,Double>composition = new HashMap<Ingredient,Double>();
        double totalAmount = ingredientOrdered.getRate()*qtyOrdered;
        composition.put(ingredientOrdered, totalAmount);
        PurchaseOrder po = new PurchaseOrder(totalAmount,composition);
        expenseList.add(new Expense(totalAmount, po, ExpenseType.PURCHASE));
        availableMoney -= totalAmount;
    }
    public static Recipe selectRecipe(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter recipe name you want to order");
        String recipeName = sc.nextLine();
        for(int i=0; i<recipeList.size(); i++){
            if(recipeList.get(i).getName().toLowerCase().compareTo(recipeName.toLowerCase())==0){
                Recipe recipe = recipeList.get(i);
                return recipe;
            }
        }
        throw new RecipeNotFoundException("Recipe '"+recipeName+"' not Found!");
    }
    public static void purchaseMultipleIngredients(Map<Ingredient,Double> ingredientsToOrder){
        double moneySpent = 0;
        for(int i=0; i<ingredientList.size(); i++){
            if(ingredientsToOrder.containsKey(ingredientList.get(i))){
                double oldQty = ingredientList.get(i).getQuantity();
                double quantityToOrder = ingredientsToOrder.get(ingredientList.get(i));
                moneySpent += quantityToOrder * ingredientList.get(i).getRate();
                ingredientList.get(i).setQuantity(quantityToOrder+oldQty);
            }
        }
        PurchaseOrder po = new PurchaseOrder(moneySpent,ingredientsToOrder);
        Expense expense = new Expense(moneySpent, po, ExpenseType.PURCHASE);
        expenseList.add(expense);
        availableMoney -= moneySpent;
    }
    public static void finalizeOrder(Recipe recipe){
        Map<Ingredient,Double> composition = recipe.getComposition();
        for(int i=0; i<ingredientList.size(); i++){
            if(composition.containsKey(ingredientList.get(i))){
                double oldQty = ingredientList.get(i).getQuantity();
                double newQuantity =  ingredientList.get(i).getQuantity()-composition.get(ingredientList.get(i));
                ingredientList.get(i).setQuantity(newQuantity);
            }
        }
        Order order = new Order(recipe, recipe.getAmount());
        Sales sale = new Sales(recipe.getAmount(),order);
        salesList.add(sale);
        availableMoney += recipe.getAmount();
    }
}
