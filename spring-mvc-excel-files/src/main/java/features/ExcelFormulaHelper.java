package features;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.*;

public class ExcelFormulaHelper {

    // Method to create Excel formulas for summation and other calculations
    public void createFormulas(Sheet sheet) throws IOException {
        Workbook workbook = sheet.getWorkbook();

        // Create headers
        String[] headers = {"Product", "Price", "Quantity", "Total"};
        createHeaderRow(sheet, headers);

        // Sample data
        Object[][] data = {
                {"Product A", 10, 5},
                {"Product B", 15, 3},
                {"Product C", 7, 10},
                {"Product D", 12, 8}
        };

        // Populate the sheet with data and formulas
        populateSheetWithData(sheet, data);

        // Save the workbook to a file
        try (FileOutputStream fileOut = new FileOutputStream("sales_with_formulas.xlsx")) {
            workbook.write(fileOut);
            System.out.println("Workbook with formulas saved as sales_with_formulas.xlsx");
        }
    }

    // Method to create header row
    private void createHeaderRow(Sheet sheet, String[] headers) {
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            headerRow.createCell(i).setCellValue(headers[i]);
        }
    }

    // Method to populate sheet with data and add formulas
    private void populateSheetWithData(Sheet sheet, Object[][] data) {
        for (int i = 0; i < data.length; i++) {
            Row row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue((String) data[i][0]);
            setNumericCellValue(row.createCell(1), data[i][1]);
            setNumericCellValue(row.createCell(2), data[i][2]);

            // Formula for total price (Price * Quantity)
            row.createCell(3).setCellFormula(String.format("B%d*C%d", i + 2, i + 2));
        }

        // Add a SUM formula for the 'Total' column
        Row totalRow = sheet.createRow(data.length + 1);
        totalRow.createCell(2).setCellValue("Total Sales");
        totalRow.createCell(3).setCellFormula(String.format("SUM(D2:D%d)", data.length + 1));
    }

    // Helper method to set numeric cell values, handling both Integer and Double types
    private void setNumericCellValue(Cell cell, Object value) {
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Double) {
            cell.setCellValue((Double) value);
        } else {
            throw new IllegalArgumentException("Unsupported data type for cell value: " + value.getClass());
        }
    }

    public static void main(String[] args) {
        try {
            Workbook workbook = WorkbookFactory.create(true);  // Creates a new .xlsx workbook
            Sheet sheet = workbook.createSheet(WorkbookUtil.createSafeSheetName("Sales Data with Formulas"));

            ExcelFormulaHelper formulaHelper = new ExcelFormulaHelper();
            formulaHelper.createFormulas(sheet);

        } catch (IOException e) {
            System.err.println("Error occurred while creating workbook or applying formulas: " + e.getMessage());
        }
    }

    // Method to add a sample formula to a sheet
    public static void addFormula(Sheet sheet) {
        Row row = sheet.createRow(1);
        Cell cell = row.createCell(0);
        cell.setCellFormula("SUM(A2:A5)");  // Example: summing cells A2 to A5
    }

    // Method to add formulas to an Excel file
    public static void addFormulas(File inputFile) {
        try (FileInputStream fis = new FileInputStream(inputFile)) {
            Workbook workbook = WorkbookFactory.create(fis);
            Sheet sheet = workbook.getSheetAt(0);  // Get the first sheet

            // Adding a SUM formula to the last row in column D
            int lastRow = sheet.getPhysicalNumberOfRows();
            Row row = sheet.getRow(lastRow - 1);  // Get the last row
            if (row == null) {
                row = sheet.createRow(lastRow);
            }
            Cell cell = row.createCell(3); // Column D (index 3)
            cell.setCellFormula("SUM(D2:D" + (lastRow - 1) + ")");

            // Save the workbook with the formula applied
            try (FileOutputStream fos = new FileOutputStream(inputFile)) {
                workbook.write(fos);
                System.out.println("Formula applied to file: " + inputFile.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
