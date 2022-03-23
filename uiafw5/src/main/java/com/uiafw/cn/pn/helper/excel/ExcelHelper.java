package com.uiafw.cn.pn.helper.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.uiafw.cn.pn.helper.logger.LoggerHelper;
import com.uiafw.cn.pn.helper.resource.ResourceHelper;

public class ExcelHelper {
	
	private Logger log = LoggerHelper.getLogger(ExcelHelper.class);
	public File file;
	public FileInputStream fileIS;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	
	public Object[][] getExcelData(String excelLocation, String sheetName) {
		try {
			Object[][] dataSets= null;
			file = new File(excelLocation);
			fileIS = new FileInputStream(file);
			workbook = new XSSFWorkbook(fileIS);
			sheet = workbook.getSheet(sheetName);
			int totalRows = sheet.getLastRowNum();
			int totalColumns = sheet.getRow(0).getLastCellNum();
			dataSets = new Object[totalRows+1][totalColumns];
			Iterator<Row> rowIterator = sheet.rowIterator();
			int i = 0;
			while (rowIterator.hasNext()) {
				i++;
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				int j = 0;
				while (cellIterator.hasNext()) {
					j++;
					Cell cell = cellIterator.next();
					switch(cell.getCellType()) {
					case STRING:
						dataSets[i-1][j-1] = cell.getStringCellValue();
						log.info(cell.getStringCellValue());
						break;
					case BOOLEAN:
						dataSets[i-1][j-1] = cell.getBooleanCellValue();
						break;
					case NUMERIC:
						dataSets[i-1][j-1] = cell.getNumericCellValue();
						break;
					case FORMULA:
						dataSets[i-1][j-1] = cell.getCellFormula();
						break;
					default:
						break;
					}
				}
			}
			return dataSets;
		} catch (Exception e) {
			return new Object[0][0];
		} finally {
			try {
				fileIS.close();
				workbook.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void updateResult(String excelLocation, String sheetName, String testCase, String testStatus) {
		try {
			file = new File(excelLocation);
			fileIS = new FileInputStream(file);
			workbook = new XSSFWorkbook(fileIS);
			sheet = workbook.getSheet(sheetName);
			int totalRows = sheet.getLastRowNum()+1;
			for(int i = 1; i<totalRows;i++) {
				XSSFRow row = sheet.getRow(i);
				String testScript = row.getCell(0).getStringCellValue();
				if (testScript.contains(testCase)) {
					row.createCell(1).setCellValue(testStatus);
					fileIS.close();
					FileOutputStream out = new FileOutputStream(file);
					workbook.write(out);
					log.info("results updated in excel sheet");
					out.close();
					break;
				}
			}
			
		} catch (Exception e) {
			log.info(e.getCause());
		}
	}
	
	public static void main(String[] args) {
		ExcelHelper excelHelper = new ExcelHelper();
		String excelLocation = ResourceHelper.getResourcePath("\\src\\main\\resources\\configFile\\testData.xlsx");
//		Object[][] data = excelHelper.getExcelData(excelLocation, "Login");
//		for(int i = 1; i<data.length;i++) {
//			for(int j = 1;j<data.length; j++) {
//				System.out.println(data[i][j]);
//			}
//		}
		excelHelper.updateResult(excelLocation, "TestScripts", "Registration", "PASS");
		excelHelper.updateResult(excelLocation, "TestScripts", "Login", "PASS");
		excelHelper.updateResult(excelLocation, "TestScripts", "AddToCart", "FAIL");
	}
}
