package com.qa.hubspot.utils;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {
	
	public static Workbook book;
	public static Sheet sheet;
	
	public static String TEST_DATA_SHEET_PATH = "./src/main/java/com/qa/hubspot/testdata/HubSpotTestData.xlsx";
	
	public static Object[][] getTestData(String sheetName) {
		
		Object data [][] = null;
		
		
		try {
			FileInputStream ip = new FileInputStream(TEST_DATA_SHEET_PATH);
			book = WorkbookFactory.create(ip);
			sheet = book.getSheet(sheetName);
			
			data =  new Object [sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			for(int r=0; r<sheet.getLastRowNum(); r++) {
				
				for(int c=0; c<sheet.getRow(0).getLastCellNum(); c++) {
					
					//r represents row & c represents column
					data[r][c] = sheet.getRow(r+1).getCell(c).toString();
				}
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return data; 
	}

}
