package methods;

import config.ExcelConfig;
import org.apache.poi.ss.usermodel.Workbook;

public class MainConfigurationMethods {

    public static void main(String[] args) {
        String filePath = "src/main/resources/sample_excel.xlsx";

        // Step 1: Configure POI if any custom setup is needed
        ExcelConfig.configurePOI();

        // Step 2: Create a new workbook with some sample data and save it
        System.out.println("Creating a new Excel file...");
        ExcelConfig.createAndSaveSimpleExcel(filePath);

        // Step 3: Read the contents of the newly created Excel file
        System.out.println("Reading the created Excel file...");
        ExcelConfig.readExcelFile(filePath);

        // Step 4: Create a blank workbook using createNewWorkbook and save it
        System.out.println("Creating and saving a blank workbook...");
        Workbook workbook = ExcelConfig.createNewWorkbook();
        if (workbook != null) {
            String newFilePath = "src/main/resources/blank_workbook.xlsx";
            ExcelConfig.writeExcelFile(newFilePath, workbook);
        }
    }
}

