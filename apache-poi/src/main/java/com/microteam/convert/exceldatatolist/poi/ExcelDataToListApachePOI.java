package com.microteam.convert.exceldatatolist.poi;

import com.microteam.convert.exceldatatolist.FoodInfo;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelDataToListApachePOI {

    // Method to read Excel file and convert it to a list of FoodInfo objects
    public static List<FoodInfo> excelDataToListOfObjets_withApachePOI(String fileLocation) throws IOException {
        FileInputStream file = new FileInputStream(new File(fileLocation));
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);  // Read the first sheet
        List<FoodInfo> foodData = new ArrayList<>();  // List to hold FoodInfo objects
        DataFormatter dataFormatter = new DataFormatter();  // Formatter to handle various Excel cell formats

        // Loop through each row (starting from 1 to skip the header row)
        for (int n = 1; n < sheet.getPhysicalNumberOfRows(); n++) {
            Row row = sheet.getRow(n);
            FoodInfo foodInfo = new FoodInfo();  // Create a new FoodInfo object for each row
            int i = row.getFirstCellNum();

            // Set the data for the FoodInfo object
            foodInfo.setCategory(dataFormatter.formatCellValue(row.getCell(i)));
            foodInfo.setName(dataFormatter.formatCellValue(row.getCell(++i)));
            foodInfo.setMeasure(dataFormatter.formatCellValue(row.getCell(++i)));
            foodInfo.setCalories(Double.parseDouble(dataFormatter.formatCellValue(row.getCell(++i))));

            // Add the foodInfo object to the list
            foodData.add(foodInfo);
        }

        // Return the list of FoodInfo objects
        return foodData;
    }
}
