package methods;

import config.ExcelConfig;
import core.ExcelReader;
import core.ExcelWriter;
import core.ExcelFormatter;
import core.ExcelValidator;
import features.ExcelChartHelper;
import features.ExcelPivotHelper;
import features.ExcelFormulaHelper;
import models.ExcelDataModel;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import service.ExcelFileService;
import utils.ExcelFileUtils;
import utils.ExcelStyleUtils;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;

public class MainMethods {

    public static void main(String[] args) {
        try {
            // Configure POI settings
            ExcelConfig.configurePOI();

            // Define file paths
            String inputFilePath = "src/main/resources/input-file.xlsx";
            String outputFilePath = "src/main/resources/output-file.xlsx";
            String templateFilePath = "src/main/resources/template-file.xlsx";

            // Check if input file exists, otherwise generate a new template
            File inputFile = new File(inputFilePath);
            if (!inputFile.exists()) {
                System.out.println("Input file not found, creating a new one...");
                generateSampleTemplate(inputFilePath);
                System.out.println("Sample template created at: " + inputFilePath);
            }

            // Load and read data from input file
            List<ExcelDataModel> dataModels = ExcelReader.readExcelFile(inputFilePath);
            System.out.println("Read data from Excel: ");
            dataModels.forEach(System.out::println);

            // Write data to output Excel file
            ExcelWriter.writeExcelFile(outputFilePath, dataModels);
            System.out.println("Data written to Excel file: " + outputFilePath);

            // Create and format a new workbook
            Workbook workbook = WorkbookFactory.create(new File(inputFilePath));
            Sheet sheet = workbook.createSheet("FormattedSheet");

            // Header row and styles
            Row headerRow = sheet.createRow(0);
            ExcelFormatter.createHeaderRow(sheet);
            ExcelFormatter.applyHeaderStyles(sheet);

            // Adding data rows
            for (int i = 1; i <= 5; i++) {
                ExcelFormatter.addDataRow(sheet, i, "Item " + i, 100.0 * i, "2024-11-09");
            }

            // Apply formatting
            ExcelFormatter.formatColumns(sheet);

            // Validate content and output to file
            boolean isValid = ExcelValidator.validateExcelContent(sheet);
            System.out.println("Is Excel content valid? " + isValid);

            // Add chart and save progress
            ExcelChartHelper.createChart(sheet);
            saveWorkbook(workbook, "src/main/resources/with_chart.xlsx");

            // Add pivot table and save progress
            ExcelPivotHelper.createPivotTable(sheet);
            saveWorkbook(workbook, "src/main/resources/with_pivot.xlsx");

            // Add formula and save progress
            ExcelFormulaHelper.addFormula(sheet);
            saveWorkbook(workbook, "src/main/resources/with_formula.xlsx");

            // Apply custom styles and save progress
            ExcelStyleUtils.applyCustomStyle(sheet);
            saveWorkbook(workbook, "src/main/resources/with_styles.xlsx");

            // Final save of the workbook
            try (FileOutputStream fileOut = new FileOutputStream(outputFilePath)) {
                workbook.write(fileOut);
                System.out.println("Workbook saved to: " + outputFilePath);
            }

            // Process Excel file using service layer
            ExcelFileService service = new ExcelFileService();
            service.processExcelFile(inputFilePath);
            System.out.println("Processed Excel file using service layer.");

            // Generate and save sample template
            generateSampleTemplate(templateFilePath);

            // Generate and save complex chart with area reference
            generateChartWithAreaReference(sheet, "src/main/resources/complex_chart.xlsx");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to create a sample template
    static void generateSampleTemplate(String filePath) throws IOException {
        Workbook workbook = ExcelFileUtils.createNewWorkbook();
        Sheet sheet = workbook.createSheet("Template");

        // Add headers
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("product");
        headerRow.createCell(1).setCellValue("price");
        headerRow.createCell(2).setCellValue("quantity");
        headerRow.createCell(3).setCellValue("total");

        // Add sample data
        for (int i = 1; i <= 5; i++) {
            Row dataRow = sheet.createRow(i);
            dataRow.createCell(0).setCellValue("Template Item " + i);
            dataRow.createCell(1).setCellValue(100.0 * i);
            dataRow.createCell(2).setCellValue(i);
            dataRow.createCell(3).setCellValue(100.0 * i * i);
        }

        // Save the template workbook
        saveWorkbook(workbook, filePath);
        workbook.close();
    }

    // Method to generate and save chart with area reference
    static void generateChartWithAreaReference(Sheet sheet, String filePath) {
        try {
            // Sample dataset
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            dataset.addValue(10, "Sales", "Q1");
            dataset.addValue(15, "Sales", "Q2");
            dataset.addValue(20, "Sales", "Q3");
            dataset.addValue(25, "Sales", "Q4");

            // Create chart
            JFreeChart chart = ChartFactory.createBarChart(
                    "Quarterly Sales", "Quarter", "Sales", dataset);

            // Convert chart to image
            BufferedImage bufferedImage = chart.createBufferedImage(500, 300);
            ByteArrayOutputStream chartOut = new ByteArrayOutputStream();
            ChartUtils.writeChartAsPNG(chartOut, chart, 500, 300);
            byte[] chartBytes = chartOut.toByteArray();

            // Add chart as image
            Workbook workbook = sheet.getWorkbook();
            int pictureIndex = workbook.addPicture(chartBytes, Workbook.PICTURE_TYPE_PNG);
            Drawing<?> drawing = sheet.createDrawingPatriarch();
            ClientAnchor anchor = workbook.getCreationHelper().createClientAnchor();
            anchor.setCol1(5);
            anchor.setRow1(0);
            anchor.setCol2(10);
            anchor.setRow2(15);
            drawing.createPicture(anchor, pictureIndex);

            // Save the workbook with the chart
            saveWorkbook(workbook, filePath);
            System.out.println("Complex chart saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Helper method to save workbook to file
    static void saveWorkbook(Workbook workbook, String filePath) {
        try (FileOutputStream fileOut = new FileOutputStream(new File(filePath))) {
            workbook.write(fileOut);
            System.out.println("Workbook saved to: " + filePath);
        } catch (IOException e) {
            System.err.println("Error saving workbook: " + e.getMessage());
        }
    }
}
