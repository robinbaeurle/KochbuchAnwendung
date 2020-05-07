package Kochbuch;

import java.util.List;

public class Recipe {

    private String name;
    private int servings;
    private double costEuro;
    private String instructions;
    private NutritionalData nutritionalData;
    private List<Ingredient> ingredients;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public double getCostEuro() {
        return costEuro;
    }

    public void setCostEuro(double costEuro) {
        this.costEuro = costEuro;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public NutritionalData getNutritionalData() {
        return nutritionalData;
    }

    public void setNutritionalData(NutritionalData nutritionalData) {
        this.nutritionalData = nutritionalData;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
