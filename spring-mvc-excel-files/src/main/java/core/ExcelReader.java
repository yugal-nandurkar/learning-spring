package core;

import models.ExcelDataModel;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader {

    // Method to create and populate an Excel file with correct schema and sample data
    public void createAndPopulateExcelFile(String filePath) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Sheet1");

        // Create header row
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("product");
        headerRow.createCell(1).setCellValue("price");
        headerRow.createCell(2).setCellValue("quantity");
        headerRow.createCell(3).setCellValue("total");

        // Populate the Excel file with sample data
        Object[][] data = {
                {"Template Item 1", 100.0, 1, 100.0},
                {"Template Item 2", 200.0, 2, 400.0},
                {"Template Item 3", 300.0, 3, 900.0},
                {"Template Item 4", 400.0, 4, 1600.0},
                {"Template Item 5", 500.0, 5, 2500.0}
        };

        // Add data rows to the sheet
        int rowNum = 1;
        for (Object[] rowData : data) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue((String) rowData[0]); // product (String)
            row.createCell(1).setCellValue((Double) rowData[1]); // price (Numeric)
            row.createCell(2).setCellValue((Integer) rowData[2]); // quantity (Numeric)
            row.createCell(3).setCellValue((Double) rowData[3]); // total (Numeric)
        }

        // Write the workbook to the file
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
        }

        // Close the workbook
        workbook.close();
    }

    // Method to read the Excel file and return a list of ExcelDataModel objects
    public static List<ExcelDataModel> readExcelFile(String filePath) throws IOException {
        List<ExcelDataModel> dataModels = new ArrayList<>();

        try (FileInputStream file = new FileInputStream(filePath)) {
            Workbook workbook = WorkbookFactory.create(file);
            Sheet sheet = workbook.getSheetAt(0); // Get the first sheet

            // Iterate through rows, starting from the second row (skip header)
            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Skip header row

                ExcelDataModel dataModel = new ExcelDataModel();

                // Assuming that SalesData is a nested static class within ExcelDataModel
                ExcelDataModel.SalesData salesData = new ExcelDataModel.SalesData();

                // Safely retrieve each cell value
                salesData.product = getStringCellValue(row.getCell(0)); // String cell
                salesData.price = getNumericCellValue(row.getCell(1));  // Numeric cell
                salesData.quantity = (int) getNumericCellValue(row.getCell(2));  // Numeric cell
                salesData.total = getNumericCellValue(row.getCell(3));  // Numeric cell

                // Print debug statements for checking values
                System.out.println("Product: " + salesData.product);
                System.out.println("Price: " + salesData.price);
                System.out.println("Quantity: " + salesData.quantity);
                System.out.println("Total: " + salesData.total);

                // Add the sales data to the data model
                dataModels.add(dataModel);
            }
        }

        return dataModels;
    }

    // Helper method to safely extract numeric values from a cell (handles both integer and double values)
    private static double getNumericCellValue(Cell cell) {
        if (cell != null) {
            if (cell.getCellType() == CellType.NUMERIC) {
                return cell.getNumericCellValue();
            }
        }
        return 0; // Default to 0 if cell is empty or doesn't match expected types
    }

    // Helper method to extract string values from cells
    private static String getStringCellValue(Cell cell) {
        if (cell != null) {
            if (cell.getCellType() == CellType.STRING) {
                return cell.getStringCellValue();
            }
        }
        return ""; // Return empty string if the cell is empty or doesn't contain a string
    }
}
