package core;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class ExcelValidator {

    private Workbook workbook;

    public ExcelValidator(String filePath) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(new File(filePath))) {
            this.workbook = WorkbookFactory.create(fileInputStream);
        }
    }

    // Validate that the sheet exists
    public boolean isSheetPresent(String sheetName) {
        return workbook.getSheet(sheetName) != null;
    }

    // Validate headers for the first row
    public boolean validateHeaders(String sheetName, List<String> expectedHeaders) {
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) return false;

        Row headerRow = sheet.getRow(0);
        if (headerRow == null || headerRow.getPhysicalNumberOfCells() < expectedHeaders.size()) return false;

        for (int i = 0; i < expectedHeaders.size(); i++) {
            Cell cell = headerRow.getCell(i);
            if (cell == null || !cell.getStringCellValue().equalsIgnoreCase(expectedHeaders.get(i))) {
                return false;
            }
        }
        return true;
    }

    // Validate cell type at a specific position
    public boolean validateCellType(String sheetName, int rowIndex, int cellIndex, CellType expectedType) {
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) return false;

        Row row = sheet.getRow(rowIndex);
        if (row == null) return false;

        Cell cell = row.getCell(cellIndex);
        return cell != null && cell.getCellType() == expectedType;
    }

    // Check if a cell is empty at a specific position
    public boolean isCellEmpty(String sheetName, int rowIndex, int cellIndex) {
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) return true;

        Row row = sheet.getRow(rowIndex);
        if (row == null) return true;

        Cell cell = row.getCell(cellIndex);
        return cell == null || cell.getCellType() == CellType.BLANK;
    }

    // Validate all cells in a column are of a specific type
    public boolean validateColumnType(String sheetName, int columnIndex, CellType expectedType) {
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) return false;

        for (Row row : sheet) {
            Cell cell = row.getCell(columnIndex);
            if (cell != null && cell.getCellType() != expectedType) {
                return false;
            }
        }
        return true;
    }

    // Close the workbook
    public void close() throws IOException {
        if (workbook != null) {
            workbook.close();
        }
    }

    public static boolean validateExcelContent(Sheet sheet) {
        // Example validation: check if the first row has content in the first cell
        Row row = sheet.getRow(0);
        return row != null && row.getCell(0) != null && !row.getCell(0).getStringCellValue().isEmpty();
    }

}
