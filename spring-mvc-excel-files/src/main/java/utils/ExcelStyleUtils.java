package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelStyleUtils {

    // Method to create and apply a basic header cell style
    public static CellStyle createHeaderCellStyle(Workbook workbook) {
        CellStyle headerStyle = workbook.createCellStyle();

        // Set font style for header
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setColor(IndexedColors.WHITE.getIndex());
        headerStyle.setFont(headerFont);

        // Set background color for header
        headerStyle.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        // Set border
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());

        headerStyle.setBorderLeft(BorderStyle.THIN);
        headerStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());

        headerStyle.setBorderRight(BorderStyle.THIN);
        headerStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());

        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());

        return headerStyle;
    }

    // Method to create and apply a general data cell style
    public static CellStyle createDataCellStyle(Workbook workbook) {
        CellStyle dataStyle = workbook.createCellStyle();

        // Set font style for data
        Font dataFont = workbook.createFont();
        dataFont.setBold(false);
        dataFont.setColor(IndexedColors.BLACK.getIndex());
        dataStyle.setFont(dataFont);

        // Set background color for data rows
        dataStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        dataStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        // Set borders for data cells
        dataStyle.setBorderBottom(BorderStyle.THIN);
        dataStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());

        dataStyle.setBorderLeft(BorderStyle.THIN);
        dataStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());

        dataStyle.setBorderRight(BorderStyle.THIN);
        dataStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());

        dataStyle.setBorderTop(BorderStyle.THIN);
        dataStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());

        return dataStyle;
    }

    // Method to create and apply a currency cell style
    public static CellStyle createCurrencyCellStyle(Workbook workbook) {
        CellStyle currencyStyle = workbook.createCellStyle();

        // Set font style for currency cells
        Font currencyFont = workbook.createFont();
        currencyFont.setBold(false);
        currencyStyle.setFont(currencyFont);

        // Set the number format for currency
        currencyStyle.setDataFormat(workbook.createDataFormat().getFormat("$#,##0.00"));

        // Set borders
        currencyStyle.setBorderBottom(BorderStyle.THIN);
        currencyStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());

        currencyStyle.setBorderLeft(BorderStyle.THIN);
        currencyStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());

        currencyStyle.setBorderRight(BorderStyle.THIN);
        currencyStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());

        currencyStyle.setBorderTop(BorderStyle.THIN);
        currencyStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());

        return currencyStyle;
    }

    // Method to create and apply a date cell style
    public static CellStyle createDateCellStyle(Workbook workbook) {
        CellStyle dateStyle = workbook.createCellStyle();

        // Set font style for date cells
        Font dateFont = workbook.createFont();
        dateFont.setBold(false);
        dateStyle.setFont(dateFont);

        // Set the number format for dates
        dateStyle.setDataFormat(workbook.createDataFormat().getFormat("mm/dd/yyyy"));

        // Set borders for date cells
        dateStyle.setBorderBottom(BorderStyle.THIN);
        dateStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());

        dateStyle.setBorderLeft(BorderStyle.THIN);
        dateStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());

        dateStyle.setBorderRight(BorderStyle.THIN);
        dateStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());

        dateStyle.setBorderTop(BorderStyle.THIN);
        dateStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());

        return dateStyle;
    }

    // Method to set a cell's style with a specific background color
    public static void setCellBackgroundColor(Cell cell, short colorIndex) {
        Workbook workbook = cell.getSheet().getWorkbook();
        CellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(colorIndex);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cell.setCellStyle(style);
    }

    // Method to merge cells in a given range
    public static void mergeCells(Sheet sheet, int startRow, int endRow, int startCol, int endCol) {
        sheet.addMergedRegion(new CellRangeAddress(startRow, endRow, startCol, endCol));
    }

    // Method to apply font style (bold, color, etc.)
    public static Font createFont(Workbook workbook, boolean bold, short colorIndex) {
        Font font = workbook.createFont();
        font.setBold(bold);
        font.setColor(colorIndex);
        return font;
    }

    // Method to apply custom border styles to a cell
    public static void applyBorders(Cell cell, BorderStyle borderStyle, short borderColor) {
        Workbook workbook = cell.getSheet().getWorkbook();
        CellStyle style = workbook.createCellStyle();

        style.setBorderBottom(borderStyle);
        style.setBottomBorderColor(borderColor);

        style.setBorderLeft(borderStyle);
        style.setLeftBorderColor(borderColor);

        style.setBorderRight(borderStyle);
        style.setRightBorderColor(borderColor);

        style.setBorderTop(borderStyle);
        style.setTopBorderColor(borderColor);

        cell.setCellStyle(style);
    }

    // Method to set alignment for text in a cell (center, left, right)
    public static void setAlignment(Cell cell, HorizontalAlignment hAlign, VerticalAlignment vAlign) {
        Workbook workbook = cell.getSheet().getWorkbook();
        CellStyle style = workbook.createCellStyle();
        style.setAlignment(hAlign);
        style.setVerticalAlignment(vAlign);
        cell.setCellStyle(style);
    }

    // Example method to apply styles and write some data to the Excel file
    public static void applyStylesAndWriteData(Sheet sheet) {
        Workbook workbook = sheet.getWorkbook();

        // Create header style and apply it to the first row
        CellStyle headerStyle = createHeaderCellStyle(workbook);
        Row headerRow = sheet.createRow(0);
        Cell headerCell1 = headerRow.createCell(0);
        headerCell1.setCellValue("Header 1");
        headerCell1.setCellStyle(headerStyle);

        // Create data style and apply it to the second row
        CellStyle dataStyle = createDataCellStyle(workbook);
        Row dataRow = sheet.createRow(1);
        Cell dataCell1 = dataRow.createCell(0);
        dataCell1.setCellValue("Data 1");
        dataCell1.setCellStyle(dataStyle);

        // Create currency style and apply it to a cell
        CellStyle currencyStyle = createCurrencyCellStyle(workbook);
        Row currencyRow = sheet.createRow(2);
        Cell currencyCell = currencyRow.createCell(0);
        currencyCell.setCellValue(1000.50);
        currencyCell.setCellStyle(currencyStyle);

        // Apply date style to a cell
        CellStyle dateStyle = createDateCellStyle(workbook);
        Row dateRow = sheet.createRow(3);
        Cell dateCell = dateRow.createCell(0);
        dateCell.setCellValue("12/25/2024");
        dateCell.setCellStyle(dateStyle);
    }

    // Method to read an existing Excel file using WorkbookFactory
    public static Workbook readExcelFile(String filePath) throws IOException {
        try (FileInputStream fis = new FileInputStream(new File(filePath))) {
            return WorkbookFactory.create(fis); // Automatically handles both .xls and .xlsx formats
        }
    }

    // Method to save the Excel file
    public static void saveExcelFile(Workbook workbook, String filePath) throws IOException {
        try (FileOutputStream fileOut = new FileOutputStream(new File(filePath))) {
            workbook.write(fileOut);
        }
    }

    public static void main(String[] args) {
        // Example usage of styling utility methods
        try {
            // Create a new workbook
            Workbook workbook = WorkbookFactory.create(true);  // Create a new workbook (for .xlsx)
            Sheet sheet = workbook.createSheet("Sheet1");

            // Apply styles and write data
            applyStylesAndWriteData(sheet);

            // Save the workbook to a file
            saveExcelFile(workbook, "styled_example.xlsx");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void applyCustomStyle(Sheet sheet) {
        CellStyle style = sheet.getWorkbook().createCellStyle();
        Font font = sheet.getWorkbook().createFont();
        font.setColor(IndexedColors.RED.getIndex());
        style.setFont(font);

        // Apply style to all cells
        for (Row row : sheet) {
            for (Cell cell : row) {
                cell.setCellStyle(style);
            }
        }
    }

}
