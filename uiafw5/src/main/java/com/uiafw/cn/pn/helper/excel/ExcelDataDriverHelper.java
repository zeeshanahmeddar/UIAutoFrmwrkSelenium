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

public class ExcelDataDriverHelper {
	
	private Logger log = LoggerHelper.getLogger(ExcelDataDriverHelper.class);
	public File file;
	public FileInputStream fileIS;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	
	public Object[][] getExcelDataDriven(String excelLocation, String sheetName) {
		try {
			Object[][] dataSets= null;
			file = new File(excelLocation);
			fileIS = new FileInputStream(file);
			workbook = new XSSFWorkbook(fileIS);
			sheet = workbook.getSheet(sheetName);
			int totalRows = sheet.getLastRowNum();
			int totalColumns = sheet.getRow(0).getLastCellNum();
			dataSets = new Object[totalRows][totalColumns-1];
			Iterator<Row> rowIterator = sheet.rowIterator();
			int i = 0;
			while (rowIterator.hasNext()) {
				i++;
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				int j = 0;
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					if(cell.getStringCellValue().contains("Start")) {
						i=0;
						break;
					}
					switch(cell.getCellType()) {
					case STRING:
						dataSets[i-1][j++] = cell.getStringCellValue();
						log.info(cell.getStringCellValue());
						break;
					case BOOLEAN:
						dataSets[i-1][j++] = cell.getBooleanCellValue();
						break;
					case NUMERIC:
						dataSets[i-1][j++] = cell.getNumericCellValue();
						break;
					case FORMULA:
						dataSets[i-1][j++] = cell.getCellFormula();
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
	
	
	
	public static void main(String[] args) {
//		ExcelDataDriverHelper excelHelper = new ExcelDataDriverHelper();
//		String excelLocation = ResourceHelper.getResourcePath("\\src\\main\\resources\\configFile\\testDataDriven.xlsx");
//		Object[][] data = excelHelper.getExcelDataDriven(excelLocation, "LoginDataDriven");
//		for(int i = 1; i<data.length;i++) {
//			for(int j = 1;j<data.length; j++) {
//				System.out.println(data[i][j]);
//			}
//		}
//		excelHelper.updateResult(excelLocation, "TestScripts", "Registration", "PASS");
//		excelHelper.updateResult(excelLocation, "TestScripts", "Login", "PASS");
//		excelHelper.updateResult(excelLocation, "TestScripts", "AddToCart", "FAIL");
	}
}
