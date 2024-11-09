package core;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelFormatter {

    private Workbook workbook;

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

    public static void createHeaderRow(Row headerRow) {
        headerRow.createCell(0).setCellValue("Column 1");
        headerRow.createCell(1).setCellValue("Column 2");
    }

    public static void applyHeaderStyles(Row headerRow) {
        CellStyle style = headerRow.getSheet().getWorkbook().createCellStyle();
        Font font = headerRow.getSheet().getWorkbook().createFont();
        font.setBold(true);
        style.setFont(font);
        headerRow.getCell(0).setCellStyle(style);
        headerRow.getCell(1).setCellStyle(style);
    }

    public static void addDataRow(Row dataRow, int index, String itemName, double price, String date) {
        dataRow.createCell(0).setCellValue(itemName);
        dataRow.createCell(1).setCellValue(price);
        dataRow.createCell(2).setCellValue(date);
    }

    public static void formatColumns(Sheet sheet) {
        sheet.autoSizeColumn(0);  // Adjust column width for column 0
        sheet.autoSizeColumn(1);  // Adjust column width for column 1
        sheet.autoSizeColumn(2);  // Adjust column width for column 2
    }

}
