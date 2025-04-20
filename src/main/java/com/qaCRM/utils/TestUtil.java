package com.qaCRM.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
//import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestUtil {
	
	public static long PAGE_LOAD_TIMEOUT = 40;
	public static long IMPLICIT_WAIT=40;
	
	public static String TESTDATA_SHEET_PATH = "C:\\Users\\Vijay\\eclipse202503\\"
			+ "PractiveProj\\qaCRMautomation\\src\\main\\java\\com\\qaCRM\\testdata"
			+ "\\TestDataCRM.xlsx";
	
	static Workbook book;
	static Sheet sheet;
	static WebDriver driver = null;
	
	public static String getScreenshot(String testCaseName) throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(scrFile, new File(currentDir + "//screenshots//" + System.currentTimeMillis() + ".png"));
		return System.getProperty(currentDir)+"//screenshots//"+testCaseName+".png";
	}
	
//	 TakesScreenshot scrShot =((TakesScreenshot)webdriver);
//	 File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
//	 File DestFile=new File(fileWithPath);
//	 FileUtils.copyFile(SrcFile, DestFile);
//	 
	public static Object[][] getTestData(String sheetName){
		FileInputStream file = null;
		
		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		}catch(IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		Object [][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		
		for(int i=0;i<sheet.getLastRowNum();i++) {
			for(int j=0;j<sheet.getRow(0).getLastCellNum();j++) {
				
				data[i][j] = sheet.getRow(i+1).getCell(j).toString();
				
			}
		}
			return data;
	}
	
}
