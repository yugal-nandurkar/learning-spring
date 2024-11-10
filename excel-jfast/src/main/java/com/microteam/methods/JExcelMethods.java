package com.microteam.methods;

import com.microteam.convert.exceldatatolist.FoodInfo;
import com.microteam.convert.exceldatatolist.jexcelapi.ExcelDataToListOfObjectsJxl;
import jxl.read.biff.BiffException;

import java.io.IOException;
import java.util.List;

public class JExcelMethods {

    public static void main(String[] args) {
        // Replace with the correct file path to your Excel file
        String fileLocation = "src/main/resources/food_info.xls";

        try {
            // Retrieve the list of FoodInfo objects from the Excel file
            List<FoodInfo> foodData = ExcelDataToListOfObjectsJxl.excelDataToListOfObjets_withJxl(fileLocation);

            // Print out the food data
            for (FoodInfo food : foodData) {
                System.out.println("Category: " + food.getCategory());
                System.out.println("Name: " + food.getName());
                System.out.println("Measure: " + food.getMeasure());
                System.out.println("Calories: " + food.getCalories());
                System.out.println("-----------------------------------");
            }
        } catch (IOException | BiffException e) {
            System.err.println("Error reading the Excel file: " + e.getMessage());
        }
    }
}
