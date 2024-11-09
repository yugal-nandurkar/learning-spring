package core;

import models.ExcelDataModel;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelWriter {

    private Workbook workbook;
    private Sheet sheet;
    private ExcelFormatter formatter;

    public ExcelWriter(String filePath, String sheetName) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(new File(filePath))) {
            this.workbook = WorkbookFactory.create(fileInputStream);
            this.sheet = workbook.getSheet(sheetName) != null ? workbook.getSheet(sheetName) : workbook.createSheet(sheetName);
            this.formatter = new ExcelFormatter(filePath);  // Reuse the formatter for styling
        }
    }

    // Method to write a row of data to a specific row index
    public void writeRow(int rowIndex, List<String> data) {
        Row row = sheet.createRow(rowIndex);
        for (int i = 0; i < data.size(); i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(data.get(i));
        }
    }

    // Method to write a row of data with formatting
    public void writeRowWithFormat(int rowIndex, List<String> data, CellStyle style) {
        Row row = sheet.createRow(rowIndex);
        for (int i = 0; i < data.size(); i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(data.get(i));
            cell.setCellStyle(style);
        }
    }

    // Method to write headers with formatting
    public void writeHeaders(List<String> headers) {
        CellStyle headerStyle = formatter.createFontStyle(true, (short) 12, IndexedColors.WHITE);
        formatter.setBackgroundColor(headerStyle, IndexedColors.GREY_50_PERCENT);
        formatter.setBorders(headerStyle, BorderStyle.THIN, IndexedColors.BLACK);

        writeRowWithFormat(0, headers, headerStyle);
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

    public static void writeExcelFile(String filePath, List<ExcelDataModel> dataModels) throws IOException {
        Workbook workbook = new XSSFWorkbook();  // Create a new workbook (for .xlsx files)
        Sheet sheet = workbook.createSheet("Data");

        // Write headers
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Column 1");
        headerRow.createCell(1).setCellValue("Column 2");

        // Write data
        for (int i = 0; i < dataModels.size(); i++) {
            ExcelDataModel model = dataModels.get(i);
            Row row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(model.getProduct());
            row.createCell(1).setCellValue(model.getPrice());
        }

        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
        }
    }

}
