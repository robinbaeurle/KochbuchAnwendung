package Kochbuch;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class NutritionalData {
    private int kcal;
    private double protein, carbs, fat;

    public int getKcal() {
        return kcal;
    }

    public void setKcal(int kcal) {
        this.kcal = kcal;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getCarbs() {
        return carbs;
    }

    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    @JsonIgnore
    public int calculateKcalPercentageOfRefIntake() {
        return kcal * 100 / 2000;
    }
}
