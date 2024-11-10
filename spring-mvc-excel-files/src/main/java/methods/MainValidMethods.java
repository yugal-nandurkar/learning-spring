package methods;

import core.ExcelValidator;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xwpf.usermodel.*;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class MainValidMethods {

    public static void main(String[] args) {
        // Specify the file path of the Excel files for validation
        List<String> sheetsToValidate = Arrays.asList(
                "src/main/resources/input-file.xlsx",
                "src/main/resources/output-file.xlsx",
                "src/main/resources/template-file.xlsx",
                "src/main/resources/with_chart.xlsx",
                "src/main/resources/with_pivot.xlsx",
                "src/main/resources/with_formula.xlsx",
                "src/main/resources/with_styles.xlsx",
                "src/main/resources/complex_chart.xlsx"
        );

        // Generate validation report
        generateValidationReport(sheetsToValidate, "src/main/resources/validation-report.docx");
    }

    // Method to generate validation report
    public static void generateValidationReport(List<String> sheetsToValidate, String reportFilePath) {
        try (XWPFDocument doc = new XWPFDocument()) {
            // Loop through each file in the list of sheets to validate
            for (String filePath : sheetsToValidate) {
                ExcelValidator excelValidator = new ExcelValidator(filePath);
                XWPFParagraph paragraph = doc.createParagraph();
                paragraph.createRun().setText("Validation Report for: " + filePath);
                paragraph.createRun().addBreak();

                // Define the expected headers for validation
                List<String> expectedHeaders = Arrays.asList("Product", "Price", "Quantity", "Total");

                // Validate sheets
                for (int i = 0; i < excelValidator.workbook.getNumberOfSheets(); i++) {
                    String sheetName = excelValidator.workbook.getSheetName(i);
                    paragraph.createRun().setText("Validating Sheet: " + sheetName);
                    paragraph.createRun().addBreak();

                    if (excelValidator.isSheetPresent(sheetName)) {
                        if (excelValidator.validateHeaders(sheetName, expectedHeaders)) {
                            paragraph.createRun().setText("Headers are valid.");
                        } else {
                            paragraph.createRun().setText("Headers are invalid.");
                        }
                        paragraph.createRun().addBreak();

                        // Validate cell type, emptiness, etc., for each sheet
                        if (excelValidator.validateCellType(sheetName, 1, 1, CellType.NUMERIC)) {
                            paragraph.createRun().setText("Cell (1, 1) is of the correct type (NUMERIC).");
                        } else {
                            paragraph.createRun().setText("Cell (1, 1) is not of the correct type.");
                        }
                        paragraph.createRun().addBreak();

                        if (excelValidator.isCellEmpty(sheetName, 1, 0)) {
                            paragraph.createRun().setText("Cell (1, 0) is empty.");
                        } else {
                            paragraph.createRun().setText("Cell (1, 0) is not empty.");
                        }
                        paragraph.createRun().addBreak();

                        if (excelValidator.validateColumnType(sheetName, 1, CellType.NUMERIC)) {
                            paragraph.createRun().setText("All cells in column 1 are of type NUMERIC.");
                        } else {
                            paragraph.createRun().setText("Not all cells in column 1 are of the correct type.");
                        }
                        paragraph.createRun().addBreak();
                    } else {
                        paragraph.createRun().setText("Sheet '" + sheetName + "' does not exist.");
                    }
                }

                // Close the workbook after validation is complete
                excelValidator.close();
            }

            // Save the validation report to a DOCX file
            try (FileOutputStream fileOut = new FileOutputStream(reportFilePath)) {
                doc.write(fileOut);
                System.out.println("Validation report has been saved to: " + reportFilePath);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
