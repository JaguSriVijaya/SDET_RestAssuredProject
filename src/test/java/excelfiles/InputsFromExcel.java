package excelfiles;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class InputsFromExcel {
    public static Object[][] getTestData() throws IOException {
    	String path ="C:\\Vijaya\\FullStackTester\\DataForTC1.xlsx";
    	FileInputStream fis = new FileInputStream(path);
    	XSSFWorkbook wb = new XSSFWorkbook(fis);
    	XSSFSheet sh = wb.getSheetAt(0);
    	int totalrows = sh.getPhysicalNumberOfRows();
    	//System.out.println(totalrows);
        Row row = sh.getRow(0);
    	int totalcolumn = row.getLastCellNum();
    	//System.out.println(totalcolumn);
    	Object[][] data = new Object[totalrows][totalcolumn];

		for (int i = 0; i < totalrows; i++) {
			for (int j = 0; j < totalcolumn; j++) {
			
					if(sh.getRow(i).getCell(j).getCellType() == CellType.STRING) {
					data[i][j] = sh.getRow(i).getCell(j).getStringCellValue();
					//System.out.println(data[i][j]);
				    }if (sh.getRow(i).getCell(j).getCellType() == CellType.NUMERIC) {
					data[i][j] = sh.getRow(i).getCell(j).getNumericCellValue();
					//System.out.println(data[i][j]);
				} 
				}
			}

		
		wb.close();
		return data;   
		}
    }  



