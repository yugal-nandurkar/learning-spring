package features;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.image.BufferedImage;
import java.io.*;

public class ExcelChartHelper {

    // Method to create a chart and embed it in the Excel file using WorkbookFactory
    public void createChartInExcel(Sheet sheet) throws IOException {
        // Create a sample dataset for the chart
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

        // Convert chart to an image (PNG format)
        ByteArrayOutputStream chartOut = new ByteArrayOutputStream();
        ChartUtils.writeChartAsPNG(chartOut, chart, 500, 300);
        byte[] chartBytes = chartOut.toByteArray();

        // Create an InputStream from the chart byte array
        InputStream chartStream = new ByteArrayInputStream(chartBytes);

        // Add the image to the workbook (Excel)
        Workbook workbook = sheet.getWorkbook();
        int pictureIndex = workbook.addPicture(chartBytes, Workbook.PICTURE_TYPE_PNG);

        // Create a drawing canvas on the sheet
        Drawing<?> drawing = sheet.createDrawingPatriarch();

        // Define position for the image
        // (Note: We're placing the image starting from the 2nd row and 2nd column)
        ClientAnchor anchor = workbook.getCreationHelper().createClientAnchor();
        anchor.setCol1(1);  // Column 2 (B)
        anchor.setRow1(1);  // Row 2 (2)
        anchor.setCol2(6);  // Column 7 (G, spanning width)
        anchor.setRow2(15); // Row 16 (spanning height)

        // Insert the picture in the workbook at the defined position
        drawing.createPicture(anchor, pictureIndex);

        System.out.println("Chart created and embedded successfully in the Excel sheet.");
    }

    // Main method to demonstrate chart creation with WorkbookFactory
    public static void main(String[] args) throws IOException {
        // Create a workbook using WorkbookFactory for both .xls and .xlsx
        Workbook workbook = WorkbookFactory.create(true);  // true for XSSFWorkbook (for .xlsx), false for HSSFWorkbook (for .xls)
        Sheet sheet = workbook.createSheet(WorkbookUtil.createSafeSheetName("Sales Data"));

        // Create an instance of ExcelChartHelper and call the chart creation method
        ExcelChartHelper chartHelper = new ExcelChartHelper();
        chartHelper.createChartInExcel(sheet);

        // Save the workbook to a file (e.g., sales_data.xlsx)
        try (FileOutputStream fileOut = new FileOutputStream("sales_data.xlsx")) {
            workbook.write(fileOut);
        }

        // Close the workbook
        workbook.close();
    }
    // Method to create and embed a chart into the Excel sheet
    public static void createChart(Sheet sheet) throws IOException {
        // Sample dataset for the chart
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

        // Convert chart to an image (PNG format)
        ByteArrayOutputStream chartOut = new ByteArrayOutputStream();
        ChartUtils.writeChartAsPNG(chartOut, chart, 500, 300);
        byte[] chartBytes = chartOut.toByteArray();

        // Create an InputStream from the chart byte array
        InputStream chartStream = new ByteArrayInputStream(chartBytes);

        // Add the image to the workbook (Excel)
        Workbook workbook = sheet.getWorkbook();
        int pictureIndex = workbook.addPicture(chartBytes, Workbook.PICTURE_TYPE_PNG);

        // Create a drawing canvas on the sheet
        Drawing<?> drawing = sheet.createDrawingPatriarch();

        // Define position for the image
        // (Note: We're placing the image starting from the 2nd row and 2nd column)
        ClientAnchor anchor = workbook.getCreationHelper().createClientAnchor();
        anchor.setCol1(1);  // Column 2 (B)
        anchor.setRow1(1);  // Row 2 (2)
        anchor.setCol2(6);  // Column 7 (G, spanning width)
        anchor.setRow2(15); // Row 16 (spanning height)

        // Insert the picture in the workbook at the defined position
        drawing.createPicture(anchor, pictureIndex);

        System.out.println("Chart created and embedded successfully in the Excel sheet.");
    }

}
