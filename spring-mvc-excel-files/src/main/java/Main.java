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
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.util.IOUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.ChartPanel;
import service.ExcelFileService;
import utils.ExcelFileUtils;
import utils.ExcelStyleUtils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Objects;

public class Main {

    public static void main(String[] args) {
        try {
            // Configure POI settings
            ExcelConfig.configurePOI();  // Ensure this method is implemented for your configuration

            // Define input and output file paths using classpath resource location
            String inputFilePath = "input-file.xlsx";
            String outputFilePath = "output-file.xlsx";
            String templateFilePath = "template-file.xlsx";

            // Create the input file if it doesn't exist
            URL resourceUrl = Main.class.getClassLoader().getResource(inputFilePath);
            if (resourceUrl == null) {
                System.out.println("Input file not found, creating a new one...");
                generateSampleTemplate(inputFilePath);
                System.out.println("Input file created at: " + inputFilePath);
            } else {
                File inputFile = new File(resourceUrl.toURI());
                // Demonstrate reading an Excel file and mapping it to data models
                List<ExcelDataModel> dataModels = ExcelReader.readExcelFile(String.valueOf(inputFile));
                System.out.println("Read data from Excel: ");
                dataModels.forEach(System.out::println);

                // Demonstrate writing data to an Excel file
                ExcelWriter.writeExcelFile(outputFilePath, dataModels);
                System.out.println("Data written to Excel file: " + outputFilePath);

                // Create a new workbook to show formatting and styling
                Workbook workbook = WorkbookFactory.create(inputFile);
                Sheet sheet = workbook.createSheet("FormattedSheet");

                // Demonstrating the creation of headers with styles
                Row headerRow = sheet.createRow(0);
                ExcelFormatter.createHeaderRow(headerRow);
                ExcelFormatter.applyHeaderStyles(headerRow);

                // Add some data rows
                for (int i = 1; i <= 5; i++) {
                    Row dataRow = sheet.createRow(i);
                    ExcelFormatter.addDataRow(dataRow, i, "Item " + i, 100.0 * i, "2024-11-09");
                }

                // Format columns, apply borders, and adjust widths
                ExcelFormatter.formatColumns(sheet);

                // Validate the Excel content
                boolean isValid = ExcelValidator.validateExcelContent(sheet);
                System.out.println("Is Excel content valid? " + isValid);

                // Create and add a chart to the Excel file using the helper
                ExcelChartHelper.createChart(sheet);
                System.out.println("Chart added to the sheet.");

                // Create a pivot table in the Excel file
                ExcelPivotHelper.createPivotTable(sheet);
                System.out.println("Pivot table created in the sheet.");

                // Add a formula to a cell in the sheet
                ExcelFormulaHelper.addFormula(sheet);
                System.out.println("Formula added to the sheet.");

                // Apply custom styles to the entire sheet
                ExcelStyleUtils.applyCustomStyle(sheet);
                System.out.println("Custom styles applied to the sheet.");

                // Save the workbook to file
                try (FileOutputStream fileOut = new FileOutputStream(new File(outputFilePath))) {
                    workbook.write(fileOut);
                    System.out.println("Workbook saved to: " + outputFilePath);
                }
            }

            // Example of file handling and validation
            boolean isValidFile = ExcelFileUtils.isValidExcelFile(new File(inputFilePath));
            System.out.println("Is input file valid? " + isValidFile);

            // Process the Excel file using the service layer
            ExcelFileService service = new ExcelFileService();
            service.processExcelFile(inputFilePath);
            System.out.println("Processed Excel file using service layer.");

            // Additional Example: Generate a new Excel file with a template
            generateSampleTemplate(templateFilePath);
            System.out.println("Sample template created at: " + templateFilePath);

            // Demonstrate using the ExcelValidator with a real file
            Workbook validatedWorkbook = ExcelFileUtils.openWorkbook(inputFilePath);
            Sheet validatedSheet = validatedWorkbook.getSheetAt(0);
            if (ExcelValidator.validateExcelContent(validatedSheet)) {
                System.out.println("Validated sheet is valid.");
            }

            // Generate a complex chart with references
            Workbook workbook = WorkbookFactory.create(new File(inputFilePath));
            Sheet sheet = workbook.getSheetAt(0); // Ensure sheet is properly initialized
            generateChartWithAreaReference(sheet);
            System.out.println("Complex chart with area reference generated.");

        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    // Example method to create a sample template
    private static void generateSampleTemplate(String filePath) throws IOException {
        Workbook workbook = ExcelFileUtils.createNewWorkbook();
        Sheet sheet = workbook.createSheet("Template");

        // Add headers to the sheet
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("product");
        headerRow.createCell(1).setCellValue("price");
        headerRow.createCell(2).setCellValue("quantity");
        headerRow.createCell(3).setCellValue("total");

        // Add data to the rows
        for (int i = 1; i <= 5; i++) {
            Row dataRow = sheet.createRow(i);
            dataRow.createCell(0).setCellValue("Template Item " + i);  // product
            dataRow.createCell(1).setCellValue(100.0 * i);               // price
            dataRow.createCell(2).setCellValue(i);                       // quantity
            dataRow.createCell(3).setCellValue(100.0 * i * i);          // total (price * quantity)
        }

        // Save the workbook to the file
        try (FileOutputStream fileOut = new FileOutputStream(new File(filePath))) {
            workbook.write(fileOut);
        }
        workbook.close();
    }

    // Method to generate a chart with an area reference
    private static void generateChartWithAreaReference(Sheet sheet) {
        try {
            // Create a sample dataset for the chart (replace with real data if needed)
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            dataset.addValue(10, "Sales", "Q1");
            dataset.addValue(15, "Sales", "Q2");
            dataset.addValue(20, "Sales", "Q3");
            dataset.addValue(25, "Sales", "Q4");

            // Create a chart using JFreeChart
            JFreeChart chart = ChartFactory.createBarChart(
                    "Quarterly Sales",  // Chart title
                    "Quarter",          // X-axis label
                    "Sales",            // Y-axis label
                    dataset             // Dataset
            );

            // Convert the JFreeChart to a BufferedImage
            BufferedImage bufferedImage = chart.createBufferedImage(500, 300);

            // Write the image to a byte array output stream
            ByteArrayOutputStream chartOut = new ByteArrayOutputStream();
            ChartUtils.writeChartAsPNG(chartOut, chart, 500, 300);
            byte[] chartBytes = chartOut.toByteArray();

            // Add the chart as an image to the Excel workbook
            Workbook workbook = sheet.getWorkbook();
            int pictureIndex = workbook.addPicture(chartBytes, Workbook.PICTURE_TYPE_PNG);

            // Create a drawing object on the sheet
            Drawing<?> drawing = sheet.createDrawingPatriarch();

            // Create an anchor to specify the position of the chart
            ClientAnchor anchor = workbook.getCreationHelper().createClientAnchor();
            anchor.setCol1(5);  // Position chart at column F (5)
            anchor.setRow1(0);  // Position chart at row 1 (0-based index)
            anchor.setCol2(10); // Set chart to span columns F to J
            anchor.setRow2(15); // Set chart to span rows 1 to 15

            // Insert the image at the anchor's location
            drawing.createPicture(anchor, pictureIndex);

            System.out.println("Chart created and embedded successfully in the Excel sheet.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
