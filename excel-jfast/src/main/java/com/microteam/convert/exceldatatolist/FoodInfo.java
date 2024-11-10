package com.microteam.convert.exceldatatolist;

import com.poiji.annotation.ExcelCellName;

public class FoodInfo {

    @ExcelCellName("Category")
    private String category; // Food category

    @ExcelCellName("Name")
    private String name; // Food name

    @ExcelCellName("Measure")
    private String measure; // Unit of measure for the food

    @ExcelCellName("Calories")
    private double calories; // Amount of calories in kcal/measure

    // Override toString for easy printing of the object
    @Override
    public String toString() {
        return "FoodInfo{" +
                "category='" + category + '\'' +
                ", name='" + name + '\'' +
                ", measure='" + measure + '\'' +
                ", calories=" + calories +
                "} \n";
    }

    // Getters and setters for each field
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }
}
