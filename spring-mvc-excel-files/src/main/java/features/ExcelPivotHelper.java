package features;

import org.apache.poi.ss.usermodel.*;
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
        Sheet pivotSheet = workbook.createSheet(WorkbookUtil.createSafeSheetName("Pivot Table"));

        // Here, we would normally add logic to create the pivot table, but as we're
        // not using `XSSFPivotTable` or `HSSFPivotTable`, we simulate pivot-like behavior
        // by creating a summarized view manually

        // Sample pivot table logic here: group by region and sum sales
        pivotSheet.createRow(0).createCell(0).setCellValue("Region");
        pivotSheet.createRow(1).createCell(0).setCellValue("North");
        pivotSheet.createRow(2).createCell(0).setCellValue("South");
        pivotSheet.createRow(3).createCell(0).setCellValue("East");
        pivotSheet.createRow(4).createCell(0).setCellValue("West");

        // Pivoted sales data by region (as an example, sum per region)
        pivotSheet.createRow(0).createCell(1).setCellValue("Total Sales");
        pivotSheet.createRow(1).createCell(1).setCellValue(1800);  // North Total Sales
        pivotSheet.createRow(2).createCell(1).setCellValue(2300);  // South Total Sales
        pivotSheet.createRow(3).createCell(1).setCellValue(3300);  // East Total Sales
        pivotSheet.createRow(4).createCell(1).setCellValue(1200);  // West Total Sales

        // Save the workbook to a file
        try (FileOutputStream fileOut = new FileOutputStream("pivot_table_using_factory.xlsx")) {
            workbook.write(fileOut);
        }
    }

    public static void main(String[] args) throws IOException {
        // Load the workbook using WorkbookFactory (which works for both .xls and .xlsx)
        try (InputStream inp = ExcelPivotHelper.class.getResourceAsStream("/path/to/your/existing/excelfile.xlsx")) {
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
