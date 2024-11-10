package com.microteam.convert.exceldatatolist;

import com.microteam.convert.exceldatatolist.fastexcel.ExcelDataToListOfObjectsFastExcel;
import com.microteam.convert.exceldatatolist.jexcelapi.ExcelDataToListOfObjectsJxl;

import jxl.read.biff.BiffException;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ExcelDataToListOfObjectsUnitTest {


    @Test
    public void whenParsingExcelFileWithFastExcel_thenConvertsToList() throws IOException {
        List<FoodInfo> foodInfoList = ExcelDataToListOfObjectsFastExcel.excelDataToListOfObjets_withFastExcel("src/main/resources/food_info.xlsx");

        assertEquals("Beverages", foodInfoList.get(0).getCategory());
        assertEquals("Dairy", foodInfoList.get(3).getCategory());
    }

    @Test
    public void whenParsingExcelFileWithJxl_thenConvertsToList() throws IOException, BiffException {
        List<FoodInfo> foodInfoList = ExcelDataToListOfObjectsJxl.excelDataToListOfObjets_withJxl("src/main/resources/food_info.xls");

        assertEquals("Beverages", foodInfoList.get(0).getCategory());
        assertEquals("Dairy", foodInfoList.get(3).getCategory());
    }

}
