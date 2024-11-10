package com.microteam.methods;

import com.microteam.jexcel.JExcelHelper;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JEHelperMethods {

    public static void main(String[] args) {
        // Replace with the correct file path to your input Excel file
        String fileLocation = "src/main/resources/food_info.xls";

        JExcelHelper jExcelHelper = new JExcelHelper();

        try {
            // Reading Excel file and printing the contents
            System.out.println("Reading Excel Data:");
            Map<Integer, List<String>> excelData = jExcelHelper.readJExcel(fileLocation);
            excelData.forEach((rowNum, rowData) -> {
                System.out.println("Row " + rowNum + ": " + rowData);
            });

            // Writing data to a new Excel file
            System.out.println("\nWriting data to Excel...");
            jExcelHelper.writeJExcel();
            System.out.println("Data written to Excel file successfully.");

        } catch (IOException | BiffException e) {
            System.err.println("Error processing Excel file: " + e.getMessage());
        } catch (WriteException e) {
            throw new RuntimeException(e);
        }
    }
}
