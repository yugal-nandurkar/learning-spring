package core;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.util.CellRangeAddress;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelFormatter {

    private Workbook workbook;

    // Constructor to load the workbook from a file
    public ExcelFormatter(String filePath) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(new File(filePath))) {
            this.workbook = WorkbookFactory.create(fileInputStream);
        }
    }

    // Method to apply font style to a cell
    public CellStyle createFontStyle(boolean isBold, short fontSize, IndexedColors fontColor) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(isBold);
        font.setFontHeightInPoints(fontSize);
        font.setColor(fontColor.getIndex());
        style.setFont(font);
        return style;
    }

    // Method to set background color
    public CellStyle setBackgroundColor(CellStyle style, IndexedColors color) {
        style.setFillForegroundColor(color.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return style;
    }

    // Method to apply borders
    public CellStyle setBorders(CellStyle style, BorderStyle borderStyle, IndexedColors borderColor) {
        short borderColorIndex = borderColor.getIndex();
        style.setBorderTop(borderStyle);
        style.setTopBorderColor(borderColorIndex);
        style.setBorderBottom(borderStyle);
        style.setBottomBorderColor(borderColorIndex);
        style.setBorderLeft(borderStyle);
        style.setLeftBorderColor(borderColorIndex);
        style.setBorderRight(borderStyle);
        style.setRightBorderColor(borderColorIndex);
        return style;
    }

    // Method to set cell alignment
    public CellStyle setAlignment(CellStyle style, HorizontalAlignment hAlign, VerticalAlignment vAlign) {
        style.setAlignment(hAlign);
        style.setVerticalAlignment(vAlign);
        return style;
    }

    // Method to create and add a data row at a specific index
    public static void addDataRow(Sheet sheet, int rowIndex, String itemName, double price, String date) {
        // Create a new row at the specified index
        Row dataRow = sheet.createRow(rowIndex);
        dataRow.createCell(0).setCellValue(itemName);
        dataRow.createCell(1).setCellValue(price);
        dataRow.createCell(2).setCellValue(date);
    }

    // Method to get the workbook
    public Workbook getWorkbook() {
        return this.workbook;
    }

    // Save the workbook to a file
    public void saveToFile(String outputFilePath) throws IOException {
        try (FileOutputStream fileOut = new FileOutputStream(outputFilePath)) {
            workbook.write(fileOut);
        }
    }

    // Close the workbook
    public void close() throws IOException {
        if (workbook != null) {
            workbook.close();
        }
    }

    // Method to create a header row in the sheet
    public static void createHeaderRow(Sheet sheet) {
        Row headerRow = sheet.createRow(0);  // Create a header row at index 0
        headerRow.createCell(0).setCellValue("Column 1");
        headerRow.createCell(1).setCellValue("Column 2");
        headerRow.createCell(2).setCellValue("Column 3");
    }

    // Method to apply styles to header row
    public static void applyHeaderStyles(Sheet sheet) {
        Row headerRow = sheet.getRow(0);  // Get the header row at index 0
        CellStyle style = sheet.getWorkbook().createCellStyle();
        Font font = sheet.getWorkbook().createFont();
        font.setBold(true);
        style.setFont(font);

        // Apply the style to each header cell
        for (int i = 0; i < headerRow.getPhysicalNumberOfCells(); i++) {
            headerRow.getCell(i).setCellStyle(style);
        }
    }

    // Method to format columns (auto-size columns)
    public static void formatColumns(Sheet sheet) {
        for (int i = 0; i < sheet.getRow(0).getPhysicalNumberOfCells(); i++) {
            sheet.autoSizeColumn(i);  // Auto-size each column
        }
    }

    // Method to add a merge cell range (optional)
    public static void mergeCells(Sheet sheet) {
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 2)); // Merge the header row from column 0 to 2
    }
}
