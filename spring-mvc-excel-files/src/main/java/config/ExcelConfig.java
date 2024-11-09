package config;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelConfig {

    // Method to configure POI settings (if needed)
    public static void configurePOI() {
        System.out.println("Configuring POI settings...");
        // Any custom POI setup code goes here if necessary
    }

    // Method to read an Excel file and print its contents
    public static void readExcelFile(String filePath) {
        try (FileInputStream fileInputStream = new FileInputStream(new File(filePath));
             Workbook workbook = WorkbookFactory.create(fileInputStream)) {

            // Read the first sheet from the Excel file
            Sheet sheet = workbook.getSheetAt(0);

            // Iterate through rows in the sheet
            for (Row row : sheet) {
                // Iterate through columns (cells) in the current row
                for (Cell cell : row) {
                    // Process cell content based on its type
                    switch (cell.getCellType()) {
                        case STRING:
                            System.out.println("Text: " + cell.getStringCellValue());
                            break;
                        case NUMERIC:
                            System.out.println("Numeric: " + cell.getNumericCellValue());
                            break;
                        case BOOLEAN:
                            System.out.println("Boolean: " + cell.getBooleanCellValue());
                            break;
                        default:
                            System.out.println("Unknown cell type");
                            break;
                    }
                }
            }

        } catch (IOException e) {
            System.err.println("Error reading the Excel file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error processing the Excel file: " + e.getMessage());
        }
    }

    // Method to write to an Excel file
    public static void writeExcelFile(String filePath, Workbook workbook) {
        try (FileOutputStream fileOut = new FileOutputStream(new File(filePath))) {
            workbook.write(fileOut);
            System.out.println("Workbook saved to: " + filePath);
        } catch (IOException e) {
            System.err.println("Error saving the Excel file: " + e.getMessage());
        }
    }

    // Example method to create a new Workbook and add a sheet with data
    public static Workbook createNewWorkbook() {
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(true); // Create a new workbook
        } catch (IOException e) {
            System.err.println("Error creating new workbook: " + e.getMessage());
        }
        return workbook;
    }

    // Method to create and save a simple workbook without charts
    public static void createAndSaveSimpleExcel(String filePath) {
        Workbook workbook = createNewWorkbook();
        if (workbook != null) {
            Sheet sheet = workbook.createSheet("Sheet1");

            // Add more data to the sheet
            for (int i = 1; i <= 5; i++) {
                Row row = sheet.createRow(i);
                row.createCell(0).setCellValue("Data " + i);
                row.createCell(1).setCellValue(100 * i);
            }

            // Write the workbook to a file
            writeExcelFile(filePath, workbook);
        }
    }
}
