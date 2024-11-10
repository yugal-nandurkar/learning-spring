package com.microteam.methods;

import com.microteam.convert.exceldatatolist.FoodInfo;
import com.microteam.convert.exceldatatolist.poi.ExcelDataToListApachePOI;

import java.io.IOException;
import java.util.List;

public class POIListMethods {
    public static void main(String[] args) {
        try {
            // Define the file location
            String fileLocation = "src/main/resources/food_info.xlsx";

            // Call the method from ExcelDataToListApachePOI to read the Excel data
            List<FoodInfo> foodDataList = ExcelDataToListApachePOI.excelDataToListOfObjets_withApachePOI(fileLocation);

            // Iterate over the list and print the food data
            for (FoodInfo foodInfo : foodDataList) {
                System.out.println("Category: " + foodInfo.getCategory());
                System.out.println("Name: " + foodInfo.getName());
                System.out.println("Measure: " + foodInfo.getMeasure());
                System.out.println("Calories: " + foodInfo.getCalories());
                System.out.println("---------------------------");
            }
        } catch (IOException e) {
            System.err.println("Error reading the Excel file: " + e.getMessage());
        }
    }
}
