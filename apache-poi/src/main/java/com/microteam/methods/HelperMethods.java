package com.microteam.methods;

import com.microteam.poi.excel.ExcelPOIHelper;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class HelperMethods {

    public static void main(String[] args) {
        ExcelPOIHelper excelPOIHelper = new ExcelPOIHelper();

        // File path for the Excel file to read
        String fileLocation = "src/main/resources/food_info.xlsx";

        try {
            // Reading data from the Excel file and printing it
            Map<Integer, List<String>> excelData = excelPOIHelper.readExcel(fileLocation);
            System.out.println("Excel Data Read from file: " + fileLocation);
            for (Map.Entry<Integer, List<String>> entry : excelData.entrySet()) {
                System.out.println("Row " + entry.getKey() + ": " + entry.getValue());
            }

            // Writing data to a new Excel file
            System.out.println("\nCreating a new Excel file...");
            excelPOIHelper.writeExcel();
            System.out.println("New Excel file has been created successfully at: src/main/resources/temp.xlsx");

        } catch (IOException e) {
            // Handle exception if reading or writing Excel file fails
            System.err.println("Error occurred while handling Excel file: " + e.getMessage());
        }
    }
}
