package service;

import core.ExcelReader;
import models.ExcelDataModel;
import org.apache.poi.ss.usermodel.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileService {

    // Method to read all rows from the first sheet of an Excel file
    public List<List<String>> readExcelFile(String filePath) throws IOException {
        List<List<String>> data = new ArrayList<>();

        try (FileInputStream file = new FileInputStream(filePath)) {
            // Create a workbook instance
            Workbook workbook = WorkbookFactory.create(file);
            Sheet sheet = workbook.getSheetAt(0); // Get the first sheet

            // Iterate through the rows
            for (Row row : sheet) {
                List<String> rowData = new ArrayList<>();
                for (Cell cell : row) {
                    rowData.add(cellToString(cell)); // Convert cell value to string and add to row data
                }
                data.add(rowData); // Add the row to the data list
            }
        }

        return data;
    }

    // Method to convert a cell to a string (handles different cell types)
    private String cellToString(Cell cell) {
        switch (cell.getCellType()) {  // Use getCellType() instead of the deprecated getCellTypeEnum()
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case STRING:
                return cell.getStringCellValue();
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }

    // Method to write data to an Excel file
    public void writeExcelFile(String filePath, List<List<String>> data) throws IOException {
        // Create a workbook (this will default to XSSF format)
        Workbook workbook = WorkbookFactory.create(true); // True creates a new workbook
        Sheet sheet = workbook.createSheet("Sheet1"); // Create a sheet named "Sheet1"

        // Write data to the sheet
        int rowIndex = 0;
        for (List<String> rowData : data) {
            Row row = sheet.createRow(rowIndex++);
            int cellIndex = 0;
            for (String cellData : rowData) {
                Cell cell = row.createCell(cellIndex++);
                cell.setCellValue(cellData); // Set cell value from the data list
            }
        }

        // Write the workbook to the file
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
        }
    }

    // Method to append data to an existing Excel file
    public void appendToExcelFile(String filePath, List<List<String>> newData) throws IOException {
        File file = new File(filePath);
        Workbook workbook;

        try (FileInputStream fileIn = new FileInputStream(filePath)) {
            workbook = WorkbookFactory.create(fileIn); // Open existing workbook
        }

        Sheet sheet = workbook.getSheetAt(0); // Assuming appending to the first sheet

        // Find the next available row
        int nextRowIndex = sheet.getPhysicalNumberOfRows();

        // Append new data to the sheet
        for (List<String> rowData : newData) {
            Row row = sheet.createRow(nextRowIndex++);
            int cellIndex = 0;
            for (String cellData : rowData) {
                Cell cell = row.createCell(cellIndex++);
                cell.setCellValue(cellData); // Set the cell value
            }
        }

        // Write the updated workbook back to the file
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
        }
    }

    // Method to remove a row from the Excel sheet (by index)
    public void removeRow(String filePath, int rowIndex) throws IOException {
        File file = new File(filePath);
        Workbook workbook;

        try (FileInputStream fileIn = new FileInputStream(filePath)) {
            workbook = WorkbookFactory.create(fileIn);
        }

        Sheet sheet = workbook.getSheetAt(0);
        Row row = sheet.getRow(rowIndex);

        if (row != null) {
            sheet.removeRow(row); // Remove the row
        }

        // Shift the remaining rows up
        int lastRow = sheet.getPhysicalNumberOfRows();
        for (int i = rowIndex; i < lastRow - 1; i++) {
            Row currentRow = sheet.getRow(i + 1);
            Row nextRow = sheet.getRow(i);

            if (currentRow != null) {
                copyRow(sheet, currentRow, nextRow);
            }
        }

        // Write the updated workbook back to the file
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
        }
    }

    // Helper method to copy data from one row to another
    private void copyRow(Sheet sheet, Row sourceRow, Row destinationRow) {
        for (int i = 0; i < sourceRow.getPhysicalNumberOfCells(); i++) {
            Cell sourceCell = sourceRow.getCell(i);
            Cell destinationCell = destinationRow.createCell(i);
            destinationCell.setCellValue(cellToString(sourceCell));
        }
    }

    public static void main(String[] args) throws IOException {
        ExcelFileService fileService = new ExcelFileService();

        // Reading data from an existing Excel file
        List<List<String>> data = fileService.readExcelFile("src/main/resources/sales_data.xlsx");

        // Print the data read from the file (for demonstration)
        for (List<String> row : data) {
            System.out.println(row);
        }

        // Example data to write
        List<List<String>> newData = new ArrayList<>();
        List<String> newRow = new ArrayList<>();
        newRow.add("Product A");
        newRow.add("15");
        newRow.add("10");
        newRow.add("150");
        newData.add(newRow);

        // Writing data to a new Excel file
        fileService.writeExcelFile("src/main/resources/new_data.xlsx", newData);
        System.out.println("Data has been written to 'new_data.xlsx'.");

        // Appending data to an existing Excel file
        fileService.appendToExcelFile("src/main/resources/sales_data_copy.xlsx", newData);
        System.out.println("Data has been appended to 'sales_data_copy.xlsx'.");

        // Removing a row from an Excel file
        fileService.removeRow("src/main/resources/sales_data_copy.xlsx", 1);
        System.out.println("Row 1 has been removed from 'sales_data_copy.xlsx'.");
    }

    public void processExcelFile(String filePath) throws IOException {
        // Here, we're using ExcelReader to read the file and return a List of ExcelDataModel objects
        List<ExcelDataModel> dataModels = ExcelReader.readExcelFile(filePath);

        // Perform some processing on dataModels (e.g., modifying or analyzing data)
        for (ExcelDataModel model : dataModels) {
            // Example processing: Print out the model data
            System.out.println("Processing data for: " + model.getName());
        }

        // You can also add more complex logic depending on the data and use cases
        System.out.println("Processing Excel file...");
    }
}
