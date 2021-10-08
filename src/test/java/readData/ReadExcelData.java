package readData;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelData {
	
	
	public ArrayList<String> getExcelData(String testCaseName, String sheetName) throws IOException{
		
		ArrayList<String> dataList = new ArrayList<String>();
		
		FileInputStream fis = new FileInputStream("D:\\thangaraj_worksppace\\APIAutomation\\src\\test\\java\\resources\\TestData.xlsx");
		
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		
		int noOfSheets = workbook.getNumberOfSheets();
		
		for(int index=0; index<noOfSheets; index++) {
			
			if(workbook.getSheetName(index).equalsIgnoreCase(sheetName)) {
				XSSFSheet sheet = workbook.getSheetAt(index);
				
				Iterator<Row> rows = sheet.iterator();
				Row firstRow = rows.next();
				Iterator<Cell> cells = firstRow.cellIterator();
				int testCaseNoRow = 0;
				int column = 0;
				
				while(cells.hasNext()) {
					
					Cell cell = cells.next();
					
					if(cell.getStringCellValue().equalsIgnoreCase("TestCase")) {
						column = testCaseNoRow;
					}
					testCaseNoRow++;
				}
				
				
				while(rows.hasNext()) {
					
					Row row= rows.next();
					
					if(row.getCell(column).getStringCellValue().equals(testCaseName)) {
						
						Iterator<Cell> innerCells = row.cellIterator();
						while(innerCells.hasNext()) {
							Cell cell = innerCells.next();
							if(cell.getCellTypeEnum()==CellType.STRING) {
								dataList.add(cell.getStringCellValue());
							}else if(cell.getCellTypeEnum() == CellType.NUMERIC) {
								dataList.add(NumberToTextConverter.toText(cell.getNumericCellValue()));
							}
						}
					}
				}
				
				
				
			}
		}
		
		return dataList;
	}

}
