package models;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ExcelDataModel {

    // Data model class for a single row in the Excel sheet
    public static class SalesData {
        // Directly accessible fields
        public String product;
        public double price;
        public int quantity;
        public double total;

        // Constructor to initialize SalesData
        public SalesData(String product, double price, int quantity, double total) {
            this.product = product;
            this.price = price;
            this.quantity = quantity;
            this.total = total;
        }

        public SalesData() {

        }
    }

    // A field for SalesData in the ExcelDataModel
    public SalesData salesData;

    // Constructor for ExcelDataModel
    public ExcelDataModel() {}

    // Constructor for ExcelDataModel with parameters
    public ExcelDataModel(String product, double price, int quantity) {
        this.salesData = new SalesData(product, price, quantity, price * quantity);  // Calculate total from price * quantity
    }

    // Method to map SalesData to ExcelDataModel
    public ExcelDataModel mapSalesDataToExcelDataModel(SalesData salesData) {
        ExcelDataModel model = new ExcelDataModel();
        model.salesData = salesData;  // Assign the SalesData to the ExcelDataModel's field
        return model;
    }

    // Method to use streams for converting SalesData list to ExcelDataModel list
    public List<ExcelDataModel> convertSalesDataToExcelDataModel(List<SalesData> salesDataList) {
        return salesDataList.stream()
                .map(this::mapSalesDataToExcelDataModel)
                .collect(Collectors.toList());
    }

    // Method to read Excel file data and return it as a list of ExcelDataModel
    public List<ExcelDataModel> readExcelFileAsExcelDataModel(String filePath) throws IOException {
        List<SalesData> salesDataList = readExcelData(filePath);
        return convertSalesDataToExcelDataModel(salesDataList);
    }

    // Method to read data from an Excel file and map it to a list of SalesData objects
    public List<SalesData> readExcelData(String filePath) throws IOException {
        List<SalesData> salesDataList = new ArrayList<>();

        // Open the workbook
        try (FileInputStream file = new FileInputStream(filePath)) {
            Workbook workbook = WorkbookFactory.create(file); // Handles both .xls and .xlsx
            Sheet sheet = workbook.getSheetAt(0); // Read the first sheet

            // Iterate through the rows and extract data
            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    // Skip the header row
                    continue;
                }

                String product = row.getCell(0) != null ? row.getCell(0).getStringCellValue() : "Unknown Product";
                double price = row.getCell(1) != null ? row.getCell(1).getNumericCellValue() : 0.0;
                int quantity = row.getCell(2) != null ? (int) row.getCell(2).getNumericCellValue() : 0;
                double total = row.getCell(3) != null ? row.getCell(3).getNumericCellValue() : 0.0;

                // Only add data if it's valid (no null or invalid values)
                if (product != null && !product.isEmpty() && price > 0 && quantity > 0) {
                    SalesData data = new SalesData(product, price, quantity, total);
                    salesDataList.add(data);
                } else {
                    System.out.println("Skipping invalid data row: " + row.getRowNum());
                }
            }
        }

        return salesDataList;
    }

    // Method to write ExcelDataModel list to an Excel file
    public void writeExcelDataFromModel(List<ExcelDataModel> excelDataModels, String filePath) throws IOException {
        Workbook workbook = WorkbookFactory.create(true); // Create a new workbook (HSSFWorkbook for .xls)

        // Create a new sheet
        Sheet sheet = workbook.createSheet("Sales Data");

        // Create header row
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Product");
        headerRow.createCell(1).setCellValue("Price");
        headerRow.createCell(2).setCellValue("Quantity");
        headerRow.createCell(3).setCellValue("Total");

        // Populate the data rows from ExcelDataModel
        int rowIndex = 1;
        for (ExcelDataModel model : excelDataModels) {
            if (model.salesData != null) {
                Row row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(model.salesData.product);
                row.createCell(1).setCellValue(model.salesData.price);
                row.createCell(2).setCellValue(model.salesData.quantity);
                row.createCell(3).setCellValue(model.salesData.total);
            }
        }

        // Write the output to a file
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
        }
    }

    // Placeholder getter and setter methods for ExcelDataModel fields
    private String product;
    private double price;
    private int quantity;
    private double total;

    // Getters and setters for the ExcelDataModel fields
    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    // New method to return the name of the product
    public String getName() {
        return getProduct(); // Returning the product name
    }

    public static void main(String[] args) throws IOException {
        ExcelDataModel model = new ExcelDataModel();

        // Reading data from an existing Excel file
        List<SalesData> salesDataList = model.readExcelData("src/main/resources/sales_data.xlsx");

        // Print out read data (for demonstration)
        for (SalesData data : salesDataList) {
            if (data != null) {
                System.out.println("Product: " + data.product + ", Price: " + data.price + ", Quantity: " + data.quantity + ", Total: " + data.total);
            }
        }

        // Converting SalesData to ExcelDataModel
        List<ExcelDataModel> excelDataModels = model.convertSalesDataToExcelDataModel(salesDataList);
        for (ExcelDataModel data : excelDataModels) {
            if (data.salesData != null) {
                System.out.println("Product: " + data.salesData.product + ", Price: " + data.salesData.price + ", Quantity: " + data.salesData.quantity + ", Total: " + data.salesData.total);
            }
        }

        // Writing ExcelDataModel data to a new Excel file
        model.writeExcelDataFromModel(excelDataModels, "src/main/resources/sales_data_copy.xlsx");
        System.out.println("Data has been written to 'sales_data_copy.xlsx'.");
    }
}
