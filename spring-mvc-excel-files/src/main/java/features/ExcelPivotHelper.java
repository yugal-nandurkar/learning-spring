package features;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ExcelPivotHelper {

    // Method to create a pivot table in Excel
    public static void createPivotTable(Sheet sheet) throws IOException {
        Workbook workbook = sheet.getWorkbook();

        // Create a simple data set for the pivot table
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Region");
        headerRow.createCell(1).setCellValue("Product");
        headerRow.createCell(2).setCellValue("Sales");

        // Add sample data
        String[][] data = {
                {"North", "Product A", "1000"},
                {"South", "Product B", "1500"},
                {"East", "Product A", "2000"},
                {"West", "Product C", "1200"},
                {"North", "Product B", "800"},
                {"East", "Product C", "1300"}
        };

        // Populate data into rows
        for (int i = 0; i < data.length; i++) {
            Row row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(data[i][0]);
            row.createCell(1).setCellValue(data[i][1]);
            row.createCell(2).setCellValue(Integer.parseInt(data[i][2]));
        }

        // Define the range of the data to be used for the pivot table
        String dataRange = "A1:C" + (data.length + 1);  // A1:C7 based on the sample data

        // Create the pivot table sheet
        XSSFSheet pivotSheet = (XSSFSheet) workbook.createSheet(WorkbookUtil.createSafeSheetName("Pivot Table"));

        // Define the pivot table
        XSSFPivotTable pivotTable = (XSSFPivotTable) pivotSheet.createPivotTable(
                new AreaReference(dataRange, workbook.getSpreadsheetVersion()), // Data range
                new CellReference("E5"), // Location for pivot table (E5)
                (XSSFSheet) sheet // Source data sheet
        );

        // Set up the pivot table by adding row and column labels, and data summary
        pivotTable.addRowLabel(0);  // Region
        pivotTable.addColumnLabel(DataConsolidateFunction.SUM, 2);  // Sales summed by region

        // Save the workbook to a file
        try (FileOutputStream fileOut = new FileOutputStream("src/main/resources/pivot_table_using_factory.xlsx")) {
            workbook.write(fileOut);
        }
    }

    public static void main(String[] args) throws IOException {
        // Load the workbook using WorkbookFactory (which works for both .xls and .xlsx)
        try (InputStream inp = ExcelPivotHelper.class.getResourceAsStream("/src/main/resources/pivot_table_using_factory.xlsx")) {
            Workbook workbook = WorkbookFactory.create(inp);  // Handles both .xls and .xlsx

            // Create a new sheet for the data
            Sheet sheet = workbook.createSheet(WorkbookUtil.createSafeSheetName("Sales Data"));

            ExcelPivotHelper pivotHelper = new ExcelPivotHelper();
            pivotHelper.createPivotTable(sheet);

            // The workbook is saved as "pivot_table_using_factory.xlsx"
            System.out.println("Pivot table created and saved as pivot_table_using_factory.xlsx");
        }
    }
}
