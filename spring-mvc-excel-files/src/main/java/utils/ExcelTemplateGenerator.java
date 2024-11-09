package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelTemplateGenerator {

    // Method to create the sample_template.xlsx
    public static void generateSampleTemplate(String filePath) throws IOException {
        Workbook workbook = WorkbookFactory.create(true); // Create a new workbook for .xlsx
        Sheet sheet = workbook.createSheet("Template");

        // Create header row
        Row headerRow = sheet.createRow(0);

        Cell headerCell1 = headerRow.createCell(0);
        headerCell1.setCellValue("ID");

        Cell headerCell2 = headerRow.createCell(1);
        headerCell2.setCellValue("Name");

        Cell headerCell3 = headerRow.createCell(2);
        headerCell3.setCellValue("Amount");

        Cell headerCell4 = headerRow.createCell(3);
        headerCell4.setCellValue("Date");

        // Style for header row
        CellStyle headerStyle = createHeaderCellStyle(workbook);
        for (int i = 0; i < 4; i++) {
            headerRow.getCell(i).setCellStyle(headerStyle);
        }

        // Create some data rows
        for (int i = 1; i <= 5; i++) {
            Row dataRow = sheet.createRow(i);

            // Add ID
            dataRow.createCell(0).setCellValue(i);

            // Add Name
            dataRow.createCell(1).setCellValue("Item " + i);

            // Add Amount (formatted as currency)
            Cell amountCell = dataRow.createCell(2);
            amountCell.setCellValue(i * 100.0);
            amountCell.setCellStyle(createCurrencyCellStyle(workbook));

            // Add Date (formatted as Date)
            Cell dateCell = dataRow.createCell(3);
            dateCell.setCellValue("2024-11-09");
            dateCell.setCellStyle(createDateCellStyle(workbook));
        }

        // Merge some cells for a title
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));

        // Set column widths for better visibility
        sheet.setColumnWidth(0, 5000); // ID column
        sheet.setColumnWidth(1, 8000); // Name column
        sheet.setColumnWidth(2, 5000); // Amount column
        sheet.setColumnWidth(3, 6000); // Date column

        // Write the workbook to a file
        try (FileOutputStream fileOut = new FileOutputStream(new File(filePath))) {
            workbook.write(fileOut);
        }

        workbook.close();
    }

    // Method to create header cell style
    private static CellStyle createHeaderCellStyle(Workbook workbook) {
        CellStyle headerStyle = workbook.createCellStyle();
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setColor(IndexedColors.WHITE.getIndex());
        headerStyle.setFont(headerFont);

        headerStyle.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

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

    // Method to create currency cell style
    private static CellStyle createCurrencyCellStyle(Workbook workbook) {
        CellStyle currencyStyle = workbook.createCellStyle();
        currencyStyle.setDataFormat(workbook.createDataFormat().getFormat("$#,##0.00"));

        // Set border styles
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

    // Method to create date cell style
    private static CellStyle createDateCellStyle(Workbook workbook) {
        CellStyle dateStyle = workbook.createCellStyle();
        dateStyle.setDataFormat(workbook.createDataFormat().getFormat("yyyy-mm-dd"));

        // Set border styles
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

    // Main method to test the template creation
    public static void main(String[] args) {
        try {
            generateSampleTemplate("sample_template.xlsx");
            System.out.println("Sample template created successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
