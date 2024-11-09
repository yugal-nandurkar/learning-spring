package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellReference;

import java.io.*;
import java.nio.file.*;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileUtils {

    // Method to check if an Excel file exists at the given path
    public static boolean fileExists(String filePath) {
        Path path = Paths.get(filePath);
        return Files.exists(path) && Files.isRegularFile(path);
    }

    // Method to create a new Excel file if it does not exist
    public static void createNewExcelFile(String filePath) throws IOException {
        if (!fileExists(filePath)) {
            try (Workbook workbook = WorkbookFactory.create(true)) { // Create a new workbook
                Sheet sheet = workbook.createSheet("Sheet1"); // Create a sheet named "Sheet1"
                try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                    workbook.write(fileOut);
                }
            }
        }
    }

    // Method to create the necessary directories for the file path
    public static void createDirectories(String filePath) throws IOException {
        Path path = Paths.get(filePath).getParent();
        if (path != null && !Files.exists(path)) {
            Files.createDirectories(path); // Create directories if they don't exist
        }
    }

    // Method to close workbook and input/output streams safely
    public static void closeResources(Workbook workbook, FileInputStream fis, FileOutputStream fos) {
        try {
            if (workbook != null) {
                workbook.close();
            }
            if (fis != null) {
                fis.close();
            }
            if (fos != null) {
                fos.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to read data from an Excel file (Returns first sheet as a 2D array of strings)
    public static String[][] readExcelFile(String filePath) throws IOException {
        String[][] data = null;
        try (FileInputStream fis = new FileInputStream(filePath)) {
            Workbook workbook = WorkbookFactory.create(fis); // Open existing workbook
            Sheet sheet = workbook.getSheetAt(0); // Read first sheet
            int rowCount = sheet.getPhysicalNumberOfRows();
            int colCount = sheet.getRow(0).getPhysicalNumberOfCells(); // Assuming uniform row length

            data = new String[rowCount][colCount];

            for (int i = 0; i < rowCount; i++) {
                Row row = sheet.getRow(i);
                for (int j = 0; j < colCount; j++) {
                    Cell cell = row.getCell(j);
                    data[i][j] = cellToString(cell); // Convert each cell to string
                }
            }
        }
        return data;
    }

    // Helper method to convert cell to string based on its type
    private static String cellToString(Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
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

    // Method to delete an Excel file
    public static void deleteExcelFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        if (Files.exists(path)) {
            Files.delete(path); // Delete the file
        }
    }

    // Method to get the value of a specific cell in an Excel file (row, column index based)
    public static String getCellValue(String filePath, int rowIndex, int colIndex) throws IOException {
        String cellValue = "";
        try (FileInputStream fis = new FileInputStream(filePath)) {
            Workbook workbook = WorkbookFactory.create(fis); // Open existing workbook
            Sheet sheet = workbook.getSheetAt(0); // Read the first sheet
            Row row = sheet.getRow(rowIndex); // Get the specific row
            if (row != null) {
                Cell cell = row.getCell(colIndex); // Get the specific cell
                cellValue = cellToString(cell); // Convert the cell value to string
            }
        }
        return cellValue;
    }

    // Method to check if a cell in the Excel file is empty (by row and column index)
    public static boolean isCellEmpty(String filePath, int rowIndex, int colIndex) throws IOException {
        String cellValue = getCellValue(filePath, rowIndex, colIndex);
        return cellValue == null || cellValue.trim().isEmpty();
    }

    // Method to count the total number of rows in an Excel file
    public static int getRowCount(String filePath) throws IOException {
        int rowCount = 0;
        try (FileInputStream fis = new FileInputStream(filePath)) {
            Workbook workbook = WorkbookFactory.create(fis); // Open existing workbook
            Sheet sheet = workbook.getSheetAt(0); // Read the first sheet
            rowCount = sheet.getPhysicalNumberOfRows(); // Get the total number of rows
        }
        return rowCount;
    }

    // Method to print the content of an Excel file (for debugging purposes)
    public static void printExcelContent(String filePath) throws IOException {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            Workbook workbook = WorkbookFactory.create(fis);
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                Iterator<Cell> cellIterator = row.iterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    System.out.print(cellToString(cell) + "\t");
                }
                System.out.println();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        String filePath = "test_data.xlsx";

        // Create directories if necessary
        createDirectories(filePath);

        // Create a new Excel file if it does not exist
        createNewExcelFile(filePath);

        // Read data from an Excel file
        String[][] data = readExcelFile(filePath);
        System.out.println("Excel file data:");
        for (String[] row : data) {
            for (String cell : row) {
                System.out.print(cell + "\t");
            }
            System.out.println();
        }

        // Get a specific cell value
        String cellValue = getCellValue(filePath, 0, 0); // Row 0, Column 0
        System.out.println("Value at cell (0,0): " + cellValue);

        // Print Excel content (for debugging)
        printExcelContent(filePath);

        // Delete the file (use with caution)
        deleteExcelFile(filePath);
    }

    public static Workbook createNewWorkbook() {
        return new XSSFWorkbook();  // Create a new workbook (for .xlsx files)
    }

    public static boolean isValidExcelFile(File file) {
        try (FileInputStream fis = new FileInputStream(file)) {
            Workbook workbook = WorkbookFactory.create(fis);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static Workbook openWorkbook(String filePath) throws IOException {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            return WorkbookFactory.create(fis);
        }
    }


}
