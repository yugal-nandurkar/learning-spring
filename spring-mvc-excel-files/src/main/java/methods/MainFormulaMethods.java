package methods;

import features.ExcelFormulaHelper;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainFormulaMethods {

    public static void main(String[] args) {
        try {
            // Create a new workbook
            Workbook workbook = WorkbookFactory.create(true);  // true for .xlsx, false for .xls format
            Sheet sheet = workbook.createSheet(WorkbookUtil.createSafeSheetName("Sales Data with Formulas"));

            // Create an instance of ExcelFormulaHelper
            ExcelFormulaHelper formulaHelper = new ExcelFormulaHelper();

            // Call the method to create formulas and populate the sheet
            formulaHelper.createFormulas(sheet);

            // Save the workbook with the formulas
            try (FileOutputStream fileOut = new FileOutputStream("src/main/resources/sales_with_formulas.xlsx")) {
                workbook.write(fileOut);
                System.out.println("Workbook with sales data and formulas saved as sales_with_formulas.xlsx");
            }

            // Add an example formula to another part of the sheet
            ExcelFormulaHelper.addFormula(sheet);

            // Demonstrate adding additional formulas
            formulaHelper.addFormulas(new File("src/main/resources/with_formula.xlsx"));

        } catch (IOException e) {
            System.err.println("Error occurred while creating workbook or applying formulas: " + e.getMessage());
        }
    }
}
