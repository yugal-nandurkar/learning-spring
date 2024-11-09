package features;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ExcelFormulaHelper {

    // Method to create Excel formulas for summation and other calculations
    public void createFormulas(Sheet sheet) throws IOException {
        Workbook workbook = sheet.getWorkbook();

        // Create headers
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Product");
        headerRow.createCell(1).setCellValue("Price");
        headerRow.createCell(2).setCellValue("Quantity");
        headerRow.createCell(3).setCellValue("Total");

        // Add sample data
        Object[][] data = {
                {"Product A", 10, 5},
                {"Product B", 15, 3},
                {"Product C", 7, 10},
                {"Product D", 12, 8}
        };

        // Populate the sheet with data
        for (int i = 0; i < data.length; i++) {
            Row row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue((String) data[i][0]);
            row.createCell(1).setCellValue((double) data[i][1]);
            row.createCell(2).setCellValue((int) data[i][2]);

            // Formula for total price (Price * Quantity)
            row.createCell(3).setCellFormula(String.format("B%d*C%d", i + 2, i + 2));
        }

        // Add a SUM formula for the 'Total' column
        Row totalRow = sheet.createRow(data.length + 1);
        totalRow.createCell(2).setCellValue("Total Sales");
        totalRow.createCell(3).setCellFormula(String.format("SUM(D2:D%d)", data.length + 1));

        // Save the workbook to a file
        try (FileOutputStream fileOut = new FileOutputStream("sales_with_formulas.xlsx")) {
            workbook.write(fileOut);
        }
    }

    public static void main(String[] args) throws IOException {
        // Load the workbook using WorkbookFactory (works for both .xls and .xlsx)
        try (InputStream inp = ExcelFormulaHelper.class.getResourceAsStream("/path/to/your/existing/excelfile.xlsx")) {
            Workbook workbook = WorkbookFactory.create(inp);  // Handles both .xls and .xlsx

            // Create a new sheet for the data
            Sheet sheet = workbook.createSheet(WorkbookUtil.createSafeSheetName("Sales Data with Formulas"));

            ExcelFormulaHelper formulaHelper = new ExcelFormulaHelper();
            formulaHelper.createFormulas(sheet);

            // The workbook is saved as "sales_with_formulas.xlsx"
            System.out.println("Sales data with formulas created and saved as sales_with_formulas.xlsx");
        }
    }

    public static void addFormula(Sheet sheet) {
        Row row = sheet.createRow(1);
        Cell cell = row.createCell(0);
        cell.setCellFormula("SUM(A2:A5)");  // Example: summing cells A2 to A5
    }

}
