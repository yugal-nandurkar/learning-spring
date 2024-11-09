package models;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ExcelDataModel {

    // Data model class for a single row in the Excel sheet
    public class SalesData {
        // Directly accessible fields
        public String product;
        public double price;
        public int quantity;
        public double total;
    }

    // Main class-level method to read data from an Excel file and map it to a list of SalesData objects
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

                SalesData data = new SalesData();

                // Directly assign values to fields (no need for setters)
                data.product = row.getCell(0).getStringCellValue();  // Set product name
                data.price = row.getCell(1).getNumericCellValue();   // Set product price
                data.quantity = (int) row.getCell(2).getNumericCellValue(); // Set quantity
                data.total = row.getCell(3).getNumericCellValue();  // Set total

                // Add to the list
                salesDataList.add(data);
            }
        }

        return salesDataList;
    }

    // Method to map SalesData to ExcelDataModel
    public ExcelDataModel mapSalesDataToExcelDataModel(SalesData salesData) {
        ExcelDataModel model = new ExcelDataModel();
        model.setProduct(salesData.product);
        model.setPrice(salesData.price);
        model.setQuantity(salesData.quantity);
        model.setTotal(salesData.total);
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
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(model.getProduct());
            row.createCell(1).setCellValue(model.getPrice());
            row.createCell(2).setCellValue(model.getQuantity());
            row.createCell(3).setCellValue(model.getTotal());
        }

        // Write the output to a file
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
        }
    }

    // Placeholder getter and setter methods for ExcelDataModel
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
        List<SalesData> salesDataList = model.readExcelData("sales_data.xlsx");

        // Print out read data (for demonstration)
        for (SalesData data : salesDataList) {
            System.out.println("Product: " + data.product + ", Price: " + data.price + ", Quantity: " + data.quantity + ", Total: " + data.total);
        }

        // Converting SalesData to ExcelDataModel
        List<ExcelDataModel> excelDataModels = model.convertSalesDataToExcelDataModel(salesDataList);
        for (ExcelDataModel data : excelDataModels) {
            System.out.println("Product: " + data.getProduct() + ", Price: " + data.getPrice() + ", Quantity: " + data.getQuantity() + ", Total: " + data.getTotal());
        }

        // Writing ExcelDataModel data to a new Excel file
        model.writeExcelDataFromModel(excelDataModels, "sales_data_copy.xlsx");
        System.out.println("Data has been written to 'sales_data_copy.xlsx'.");
    }
}
