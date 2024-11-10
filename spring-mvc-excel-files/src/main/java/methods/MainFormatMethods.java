package methods;

import core.ExcelFormatter;
import org.apache.poi.ss.usermodel.*;

import java.io.IOException;

public class MainFormatMethods {
    public static void main(String[] args) {
        String inputFilePath = "src/main/resources/input-file.xlsx";
        String outputFilePath = "src/main/resources/formatted-output.xlsx";

        try {
            // Initialize the ExcelFormatter with an existing file
            ExcelFormatter formatter = new ExcelFormatter(inputFilePath);

            // Create a new sheet or get an existing one
            Sheet sheet = formatter.getWorkbook().createSheet("FormattedSheet");

            // Create header row and apply header methods
            ExcelFormatter.createHeaderRow(sheet);  // Pass sheet, not row
            ExcelFormatter.applyHeaderStyles(sheet);  // Pass sheet, not row

            // Apply custom styles to data rows
            for (int i = 1; i <= 5; i++) {
                Row dataRow = sheet.createRow(i);

                // Create cells for each row
                dataRow.createCell(0).setCellValue("Item " + i);
                dataRow.createCell(1).setCellValue(100.0 * i);
                dataRow.createCell(2).setCellValue("2024-11-09");

                // Apply styles to the entire row
                applyRowStyle(sheet, dataRow, i);
            }

            // Format column widths
            ExcelFormatter.formatColumns(sheet);

            // Save the formatted workbook
            formatter.saveToFile(outputFilePath);
            System.out.println("Workbook formatted and saved to: " + outputFilePath);

            // Close the workbook
            formatter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to apply a style to the entire row (instead of individual cells)
    private static void applyRowStyle(Sheet sheet, Row row, int rowIndex) {
        Workbook workbook = sheet.getWorkbook();

        // Create a cell style for the row
        CellStyle rowStyle = workbook.createCellStyle();

        // Apply a font style for the row (set bold and font size for example)
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 12);
        rowStyle.setFont(font);

        // Apply background color to the row
        rowStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        rowStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        // Apply borders to all cells in the row
        rowStyle.setBorderBottom(BorderStyle.THIN);
        rowStyle.setBorderTop(BorderStyle.THIN);
        rowStyle.setBorderLeft(BorderStyle.THIN);
        rowStyle.setBorderRight(BorderStyle.THIN);

        // Apply the style to all cells in the row
        for (int i = 0; i < row.getPhysicalNumberOfCells(); i++) {
            row.getCell(i).setCellStyle(rowStyle);
        }
    }
}
