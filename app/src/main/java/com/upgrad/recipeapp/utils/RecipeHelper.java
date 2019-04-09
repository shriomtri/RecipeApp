package com.upgrad.recipeapp.utils;

import com.upgrad.recipeapp.model.Ingredient;
import com.upgrad.recipeapp.model.Recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipeHelper {

    private static List<Ingredient> ingredients = new ArrayList<>();

    public static List<Ingredient> getIngredient(Recipe recipe){

        ingredients.clear();
        List<Ingredient> ingredientList = new ArrayList<>();

        ingredientList.add(new Ingredient(recipe.getStrIngredient1(), recipe.getStrMeasure1()));
        ingredientList.add(new Ingredient(recipe.getStrIngredient2(), recipe.getStrMeasure2()));
        ingredientList.add(new Ingredient(recipe.getStrIngredient3(), recipe.getStrMeasure3()));
        ingredientList.add(new Ingredient(recipe.getStrIngredient4(), recipe.getStrMeasure4()));
        ingredientList.add(new Ingredient(recipe.getStrIngredient5(), recipe.getStrMeasure5()));
        ingredientList.add(new Ingredient(recipe.getStrIngredient6(), recipe.getStrMeasure6()));
        ingredientList.add(new Ingredient(recipe.getStrIngredient7(), recipe.getStrMeasure7()));
        ingredientList.add(new Ingredient(recipe.getStrIngredient8(), recipe.getStrMeasure8()));
        ingredientList.add(new Ingredient(recipe.getStrIngredient9(), recipe.getStrMeasure9()));
        ingredientList.add(new Ingredient(recipe.getStrIngredient10(), recipe.getStrMeasure10()));
        ingredientList.add(new Ingredient(recipe.getStrIngredient11(), recipe.getStrMeasure11()));
        ingredientList.add(new Ingredient(recipe.getStrIngredient12(), recipe.getStrMeasure12()));
        ingredientList.add(new Ingredient(recipe.getStrIngredient13(), recipe.getStrMeasure13()));
        ingredientList.add(new Ingredient(recipe.getStrIngredient14(), recipe.getStrMeasure14()));
        ingredientList.add(new Ingredient(recipe.getStrIngredient15(), recipe.getStrMeasure15()));
        ingredientList.add(new Ingredient(recipe.getStrIngredient16(), recipe.getStrMeasure16()));
        ingredientList.add(new Ingredient(recipe.getStrIngredient17(), recipe.getStrMeasure17()));
        ingredientList.add(new Ingredient(recipe.getStrIngredient18(), recipe.getStrMeasure18()));
        ingredientList.add(new Ingredient(recipe.getStrIngredient19(), recipe.getStrMeasure19()));
        ingredientList.add(new Ingredient(recipe.getStrIngredient20(), recipe.getStrMeasure20()));

        //filtering empty items from final list
        for(int i=0, size = ingredientList.size(); i<size; i++){
            Ingredient ingredient = ingredientList.get(i);
            if(!ingredient.getName().equals("") || ingredient.getName().length() > 0){
                ingredients.add(ingredient);
            }
        }

        return ingredients;
    }

    public static String[] getSteps(Recipe recipe){
        return recipe.getStrInstructions().split("\\.");
    }

}
