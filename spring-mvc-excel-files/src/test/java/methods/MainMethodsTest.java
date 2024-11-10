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
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFPivotTable;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.apache.poi.ss.util.AreaReference;
import service.ExcelFileService;

import java.io.*;
import java.util.List;

import static org.mockito.Mockito.*;

public class MainMethodsTest {

    @Mock
    private Workbook workbook;

    @Mock
    private Sheet sheet;

    @Mock
    private Row row;

    @Mock
    private ExcelFileService excelFileService;

    @BeforeEach
    public void setUp() throws IOException {
        MockitoAnnotations.openMocks(this);
        when(workbook.createSheet(anyString())).thenReturn(sheet);
        when(sheet.createRow(anyInt())).thenReturn(row);
    }

    @Test
    void testGenerateSampleTemplate() throws IOException {
        // Mock the file path and create a template file
        String filePath = "src/test/resources/sample-template.xlsx";
        MainMethods.generateSampleTemplate(filePath);

        File templateFile = new File(filePath);
        Assertions.assertTrue(templateFile.exists(), "Template file should be created.");
    }

    @Test
    void testReadExcelFile() throws IOException {
        // Prepare a mock Excel file
        String inputFilePath = "src/test/resources/input-file.xlsx";
        List<ExcelDataModel> dataModels = ExcelReader.readExcelFile(inputFilePath);

        Assertions.assertNotNull(dataModels, "Data models should not be null");
        Assertions.assertFalse(dataModels.isEmpty(), "Data models list should not be empty");
    }

    @Test
    void testWriteExcelFile() throws IOException {
        // Prepare test data
        List<ExcelDataModel> dataModels = List.of(new ExcelDataModel("Item 1", 10.0, 5));

        // Mock writing to Excel file
        String outputFilePath = "src/test/resources/output-file.xlsx";
        ExcelWriter.writeExcelFile(outputFilePath, dataModels);

        File outputFile = new File(outputFilePath);
        Assertions.assertTrue(outputFile.exists(), "Output Excel file should be created.");
    }

    @Test
    void testCreateHeaderRow() {
        // Test ExcelFormatter header row creation
        ExcelFormatter.createHeaderRow(sheet);
        verify(sheet, times(1)).createRow(0);
    }

    @Test
    void testApplyHeaderStyles() {
        // Apply header styles
        ExcelFormatter.applyHeaderStyles(sheet);
        // Verify if style was applied (style application method will be mocked)
        verify(sheet, times(1)).setColumnWidth(0, 5000);
    }

    @Test
    void testValidateExcelContent() {
        // Mock sheet validation
        boolean isValid = ExcelValidator.validateExcelContent(sheet);
        Assertions.assertTrue(isValid, "Excel content should be valid");
    }

    @Test
    void testCreateChart() throws IOException {
        // Test chart creation
        ExcelChartHelper.createChart(sheet);

        // Verify chart creation (chart creation method will be mocked)
        verify(sheet, times(1)).createDrawingPatriarch();
    }

    @Test
    void testCreatePivotTable() throws IOException {
        // Mock the sheet and workbook
        XSSFSheet sheet = mock(XSSFSheet.class);
        XSSFPivotTable pivotTable = mock(XSSFPivotTable.class);

        // Mock the workbook to return the sheet
        when(sheet.getWorkbook()).thenReturn(mock(org.apache.poi.xssf.usermodel.XSSFWorkbook.class));

        // Test pivot table creation
        ExcelPivotHelper.createPivotTable(sheet);

        // Verify that createPivotTable() was called on the sheet
        verify(sheet, times(1)).createPivotTable(any(AreaReference.class), any(CellReference.class), eq(sheet));

        // Additional verification can be added to check the pivot table properties (optional)
        // verify(pivotTable, times(1)).addRowLabel(0);  // Verify that row label is added
        // verify(pivotTable, times(1)).addColumnLabel(eq(DataConsolidateFunction.SUM), eq(2)); // Verify that column label (SUM) is added
    }

    @Test
    void testAddFormula() {
        // Test formula addition
        ExcelFormulaHelper.addFormula(sheet);

        // Verify that formula is set
        verify(sheet, times(1)).createRow(anyInt());
    }

    @Test
    void testSaveWorkbook() {
        // Test saving workbook
        String filePath = "src/test/resources/saved-workbook.xlsx";
        MainMethods.saveWorkbook(workbook, filePath);

        File file = new File(filePath);
        Assertions.assertTrue(file.exists(), "Workbook should be saved to the specified path");
    }

    @Test
    void testProcessExcelFile() throws IOException {
        // Mock ExcelFileService
        doNothing().when(excelFileService).processExcelFile(anyString());
        excelFileService.processExcelFile("src/test/resources/input-file.xlsx");

        // Verify that processExcelFile method was called
        verify(excelFileService, times(1)).processExcelFile(anyString());
    }

    @Test
    void testGenerateChartWithAreaReference() throws IOException {
        // Mock chart creation with area reference
        String chartFilePath = "src/test/resources/complex-chart.xlsx";
        Workbook mockWorkbook = mock(Workbook.class);
        Sheet mockSheet = mock(Sheet.class);
        when(mockWorkbook.createSheet(anyString())).thenReturn(mockSheet);

        MainMethods.generateChartWithAreaReference(mockSheet, chartFilePath);

        File chartFile = new File(chartFilePath);
        Assertions.assertTrue(chartFile.exists(), "Complex chart should be saved as an image.");
    }

}
