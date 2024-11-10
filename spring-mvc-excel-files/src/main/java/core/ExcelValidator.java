package core;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelValidator {

    public Workbook workbook;

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

    public static void generateValidationReport(List<String> sheetNames, String reportFilePath) {
        try (XWPFDocument doc = new XWPFDocument()) {
            // Create title paragraph
            XWPFParagraph title = doc.createParagraph();
            title.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun run = title.createRun();
            run.setText("Excel Validation Report");
            run.setBold(true);
            run.setFontSize(16);
            title.createRun().addBreak();

            // Loop through each sheet to validate and add result to the report
            for (String sheetName : sheetNames) {
                validateSheet(sheetName, doc);
            }

            // Save the document to file
            try (FileOutputStream fileOut = new FileOutputStream(reportFilePath)) {
                doc.write(fileOut);
                System.out.println("Validation report has been saved to: " + reportFilePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to validate a sheet and append results to the report
    private static void validateSheet(String sheetName, XWPFDocument doc) {
        try {
            // Load the sheet and validate its content
            Workbook workbook = WorkbookFactory.create(new File(sheetName));
            Sheet sheet = workbook.getSheetAt(0);  // Assuming we want to validate the first sheet in each file
            XWPFParagraph paragraph = doc.createParagraph();
            paragraph.createRun().setText("Validating Sheet: " + sheetName);
            paragraph.createRun().addBreak();

            // Example: validate headers (You can customize this logic)
            String[] expectedHeaders = {"product", "price", "quantity", "total"};
            boolean headersValid = validateHeaders(sheet, expectedHeaders);
            if (headersValid) {
                paragraph.createRun().setText("Headers are valid.");
            } else {
                paragraph.createRun().setText("Headers are invalid.");
            }
            paragraph.createRun().addBreak();
        } catch (IOException e) {
            XWPFParagraph errorParagraph = doc.createParagraph();
            errorParagraph.createRun().setText("Error validating sheet: " + sheetName);
            errorParagraph.createRun().addBreak();
        }
    }

    // Method to validate headers in a sheet
    private static boolean validateHeaders(Sheet sheet, String[] expectedHeaders) {
        Row headerRow = sheet.getRow(0);
        for (int i = 0; i < expectedHeaders.length; i++) {
            if (headerRow.getCell(i) == null || !headerRow.getCell(i).getStringCellValue().equals(expectedHeaders[i])) {
                return false;
            }
        }
        return true;
    }

}
