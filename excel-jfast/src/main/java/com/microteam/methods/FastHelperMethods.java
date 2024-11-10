package com.microteam.methods;

import com.microteam.fastexcel.FastexcelHelper;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class FastHelperMethods {

    public static void main(String[] args) {
        FastexcelHelper helper = new FastexcelHelper();

        // File location for reading Excel
        String readFileLocation = "src/main/resources/food_info.xlsx";  // Update this to your actual file path

        try {
            // Read Excel Data
            Map<Integer, List<String>> excelData = helper.readExcel(readFileLocation);
            System.out.println("Excel Data Read:");
            excelData.forEach((rowNum, cellValues) -> {
                System.out.println("Row " + rowNum + ": " + cellValues);
            });

            // Write data to new Excel file
            helper.writeExcel();
            System.out.println("Excel File written successfully!");

        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
                     