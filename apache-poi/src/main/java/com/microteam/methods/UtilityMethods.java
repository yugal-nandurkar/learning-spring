package com.microteam.methods;

import com.microteam.poi.excel.ExcelUtility;

import java.io.IOException;

public class UtilityMethods {

    public static void main(String[] args) {
        String filePath = "src/main/resources/food_info.xlsx"; // Path to your Excel file

        try {
            // Reading data from the Excel file using the readExcel method
            String excelContent = ExcelUtility.readExcel(filePath);
            System.out.println("Excel Content:\n" + excelContent);

        } catch (IOException e) {
            // Handle exceptions if there's an issue reading the file
            System.err.println("Error reading the Excel file: " + e.getMessage());
        }
    }
}
