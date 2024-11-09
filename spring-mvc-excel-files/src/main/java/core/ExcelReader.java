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
                {"Template Item 1", 100.0, 2, 200.0},
                {"Template Item 2", 200.0, 1, 200.0},
                {"Template Item 3", 300.0, 5, 1500.0},
                {"Template Item 4", 400.0, 3, 1200.0},
                {"Template Item 5", 500.0, 4, 2000.0}
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
                ExcelDataModel.SalesData salesData = dataModel.new SalesData();

                // Safely retrieve each cell value
                salesData.product = row.getCell(0).getStringCellValue(); // String cell
                salesData.price = row.getCell(1).getNumericCellValue();  // Numeric cell
                salesData.quantity = (int) row.getCell(2).getNumericCellValue();  // Numeric cell
                salesData.total = row.getCell(3).getNumericCellValue();  // Numeric cell

                // Add the sales data to the data model
                dataModels.add(dataModel);
            }
        }

        return dataModels;
    }

    // Main method for testing the ExcelReader functionality
    public static void main(String[] args) {
        try {
            String filePath = "input-file.xlsx"; // Update with your desired file path

            ExcelReader excelReader = new ExcelReader();

            // Create and populate the Excel file with the correct schema
            excelReader.createAndPopulateExcelFile(filePath);

            // Example: Read the Excel file and print the data
            List<ExcelDataModel> data = readExcelFile(filePath);
            for (ExcelDataModel model : data) {
                System.out.println("Product: " + model.new SalesData().product);
                System.out.println("Price: " + model.new SalesData().price);
                System.out.println("Quantity: " + model.new SalesData().quantity);
                System.out.println("Total: " + model.new SalesData().total);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
