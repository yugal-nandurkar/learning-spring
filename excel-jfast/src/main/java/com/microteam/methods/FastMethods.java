package com.microteam.methods;
import com.microteam.convert.exceldatatolist.FoodInfo;
import com.microteam.convert.exceldatatolist.fastexcel.ExcelDataToListOfObjectsFastExcel;

import java.io.IOException;
import java.util.List;

public class FastMethods {
    public static void main(String[] args) {
        // Define the path to your Excel file
        String fileLocation = "src/main/resources/food_info.xlsx"; // Replace with actual file path

        try {
            // Call the method from ExcelDataToListOfObjectsFastExcel class to convert Excel data to a list of FoodInfo objects
            List<FoodInfo> foodList = ExcelDataToListOfObjectsFastExcel.excelDataToListOfObjets_withFastExcel(fileLocation);

            // Output the list of FoodInfo objects
            for (FoodInfo food : foodList) {
                System.out.println(food);
            }
        } catch (IOException | NumberFormatException e) {
            // Handle exceptions
            System.err.println("Error occurred while processing the Excel file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
