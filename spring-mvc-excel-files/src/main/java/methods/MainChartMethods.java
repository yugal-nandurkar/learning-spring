package methods;

import features.ExcelChartHelper;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileOutputStream;
import java.io.IOException;

public class MainChartMethods {

    public static void main(String[] args) {
        // Create a workbook using WorkbookFactory for compatibility with both .xls and .xlsx formats
        try (Workbook workbook = WorkbookFactory.create(true)) { // true for XSSFWorkbook (.xlsx), false for HSSFWorkbook (.xls)

            // Create a new sheet with a safe name using WorkbookUtil
            Sheet sheet = workbook.createSheet(WorkbookUtil.createSafeSheetName("Sales Data"));

            // Create an instance of ExcelChartHelper
            ExcelChartHelper chartHelper = new ExcelChartHelper();

            // Call the method to create a chart and embed it in the sheet
            chartHelper.createChartInExcel(sheet);

            // Save the workbook to a file named "sales_chart.xlsx"
            try (FileOutputStream fileOut = new FileOutputStream("src/main/resources/sales_chart.xlsx")) {
                workbook.write(fileOut);
                System.out.println("Workbook with chart created and saved as sales_data.xlsx");
            }

        } catch (IOException e) {
            System.err.println("Error occurred while creating workbook or chart: " + e.getMessage());
        }
    }
}
