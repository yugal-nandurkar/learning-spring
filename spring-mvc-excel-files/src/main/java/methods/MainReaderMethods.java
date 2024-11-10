package methods;

import core.ExcelReader;
import models.ExcelDataModel;

import java.io.IOException;
import java.util.List;

public class MainReaderMethods {

    public static void main(String[] args) {
        try {
            String filePath = "src/main/resources/input-file.xlsx"; // Update with your desired file path

            ExcelReader excelReader = new ExcelReader();

            // Create and populate the Excel file with the correct schema
            excelReader.createAndPopulateExcelFile(filePath);

            // Example: Read the Excel file and print the data
            List<ExcelDataModel> data = ExcelReader.readExcelFile(filePath);
            for (ExcelDataModel model : data) {
                ExcelDataModel.SalesData salesData = model.salesData;
                System.out.println("Product: " + salesData.product);
                System.out.println("Price: " + salesData.price);
                System.out.println("Quantity: " + salesData.quantity);
                System.out.println("Total: " + salesData.total);
                System.out.println(); // Add a blank line between each entry for readability
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
