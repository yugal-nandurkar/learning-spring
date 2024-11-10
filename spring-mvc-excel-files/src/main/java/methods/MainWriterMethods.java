package methods;

import models.ExcelDataModel;
import models.ExcelDataModel.SalesData;

import java.io.IOException;
import java.util.List;

public class MainWriterMethods {

    public static void main(String[] args) {
        // Initialize an instance of ExcelDataModel
        ExcelDataModel model = new ExcelDataModel();

        // Specify file paths
        String inputFilePath = "src/main/resources/input-file.xlsx";
        String outputFilePath = "src/main/resources/sales_data.xlsx";

        try {
            // Step 1: Read data from the Excel file
            List<SalesData> salesDataList = model.readExcelData(inputFilePath);
            System.out.println("Sales data read from file:");
            printSalesData(salesDataList);

            // Step 2: Convert SalesData objects to ExcelDataModel format
            List<ExcelDataModel> excelDataModels = model.convertSalesDataToExcelDataModel(salesDataList);
            System.out.println("\nConverted SalesData to ExcelDataModel format:");
            printExcelDataModels(excelDataModels);

            // Step 3: Write ExcelDataModel list to a new Excel file
            model.writeExcelDataFromModel(excelDataModels, outputFilePath);
            System.out.println("\nData has been written to '" + outputFilePath + "'.");

        } catch (IOException e) {
            System.err.println("An error occurred while processing the Excel file: " + e.getMessage());
        }
    }

    // Utility method to print SalesData for demonstration
    private static void printSalesData(List<SalesData> salesDataList) {
        for (SalesData data : salesDataList) {
            System.out.println("Product: " + data.product + ", Price: " + data.price +
                    ", Quantity: " + data.quantity + ", Total: " + data.total);
        }
    }

    // Utility method to print ExcelDataModel list for demonstration
    private static void printExcelDataModels(List<ExcelDataModel> excelDataModels) {
        for (ExcelDataModel model : excelDataModels) {
            if (model.salesData != null) {
                System.out.println("Product: " + model.salesData.product + ", Price: " + model.salesData.price +
                        ", Quantity: " + model.salesData.quantity + ", Total: " + model.salesData.total);
            }
        }
    }
}
