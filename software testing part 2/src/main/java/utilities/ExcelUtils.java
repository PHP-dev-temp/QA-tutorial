package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	private static XSSFSheet ExcelWSheetXssf = null;
	private static XSSFWorkbook ExcelWBookXssf = null;
	private static XSSFCell CellXssf = null;
	private static HSSFSheet ExcelWSheetHssf = null;
	private static HSSFWorkbook ExcelWBookHssf = null;
	private static HSSFCell CellHssf = null;
	
	public static Object[][] getTableArray(String FilePath, String SheetName) throws Exception {	 	

		String fileExtn = FilenameUtils.getExtension(FilePath);
	 	String[][] tabArray = null;
		if(!new File(FilePath).isFile()){
			System.out.println("Wrong name of input Excel file!!!");
		} else {		 	
			if (fileExtn.equalsIgnoreCase("xlsx")) {
				tabArray = (String[][]) getTableArrayXssf(FilePath, SheetName);
		 	}
			if (fileExtn.equalsIgnoreCase("xls")) {
				tabArray = (String[][]) getTableArrayHssf(FilePath, SheetName);	    	  
		    }
		}
		return (tabArray); 		
	}
	
	
	public static Object[][] getTableArrayXssf(String FilePath, String SheetName) throws Exception {   
	   String[][] tabArray = null;
	
	   try {
		   FileInputStream ExcelFile = new FileInputStream(FilePath);
		   
		   // Access the required test data sheet
		   ExcelWBookXssf = new XSSFWorkbook(ExcelFile);
		   ExcelWSheetXssf = ExcelWBookXssf.getSheet(SheetName);
	
		   int startRow = 0;
		   int startCol = 0;
		   int ci,cj;
		   int totalRows = ExcelWSheetXssf.getLastRowNum();
		   // you can write a function as well to get Column count
		   int totalCols = 8;
	
		   tabArray=new String[totalRows][totalCols];
		   ci=0;
		   for (int i=startRow;i<totalRows;i++, ci++) {    
			  cj=0;
			   for (int j=startCol;j<totalCols;j++, cj++){
				   //System.out.println(ci+" - "+cj);
				   tabArray[ci][cj]=getCellDataXssf(i,j);
				   //System.out.println(tabArray[ci][cj]);  
					}
				}
			}catch (FileNotFoundException e){
				System.out.println("Could not read the Excel sheet");
				e.printStackTrace();
			}catch (IOException e){
				System.out.println("Could not read the Excel sheet");
				e.printStackTrace();
			}
	   ExcelWBookXssf.close();
	   return(tabArray);
	}
	
	public static String getCellDataXssf(int RowNum, int ColNum) throws Exception {
		
		String CellData;
		
		try{
			CellXssf = ExcelWSheetXssf.getRow(RowNum).getCell(ColNum);
			switch (CellXssf.getCellType()) {
				case Cell.CELL_TYPE_STRING:
					CellData = CellXssf.getStringCellValue();
					break;
				case Cell.CELL_TYPE_BLANK:
					CellData = "";
					break;
				case Cell.CELL_TYPE_NUMERIC:
					if(DateUtil.isCellDateFormatted(CellXssf)) {
						CellData = CellXssf.getDateCellValue() + "";
					} else {
						Double d = CellXssf.getNumericCellValue();
						Integer dtoi = d.intValue();
						Double result = d - dtoi;
						if (result > 0){
							CellData = Double.toString(d);							
						} else {
							CellData = Integer.toString(dtoi);
						}
					}
					break;
				case Cell.CELL_TYPE_BOOLEAN:
					Boolean b = CellXssf.getBooleanCellValue();
					CellData = b.toString() + "";
					break;
				default:
					CellData = "";
			}			
			return CellData;			
		}catch (Exception e) {
			System.out.println(e.getMessage());
			throw (e);
		}
	}
	
	
	public static Object[][] getTableArrayHssf(String FilePath, String SheetName) throws Exception {   
	   String[][] tabArray = null;
	
	   try {
		   FileInputStream ExcelFile = new FileInputStream(FilePath);
		   
		   // Access the required test data sheet
		   ExcelWBookHssf = new HSSFWorkbook(ExcelFile);
		   ExcelWSheetHssf = ExcelWBookHssf.getSheet(SheetName);
	
		   int startRow = 0;
		   int startCol = 0;
		   int ci,cj;
		   int totalRows = ExcelWSheetHssf.getLastRowNum();
		   // you can write a function as well to get Column count
		   int totalCols = 8;
	
		   tabArray=new String[totalRows][totalCols];
		   ci=0;
		   for (int i=startRow;i<totalRows;i++, ci++) {    
			  cj=0;
			   for (int j=startCol;j<totalCols;j++, cj++){
				   //System.out.println(ci+" - "+cj);
				   tabArray[ci][cj]=getCellDataHssf(i,j);
				   //System.out.println(tabArray[ci][cj]);  
					}
				}
			}catch (FileNotFoundException e){
				System.out.println("Could not read the Excel sheet");
				e.printStackTrace();
			}catch (IOException e){
				System.out.println("Could not read the Excel sheet");
				e.printStackTrace();
			}
	   ExcelWBookHssf.close();
	   return(tabArray);
	}
	
	public static String getCellDataHssf(int RowNum, int ColNum) throws Exception {
		
		String CellData;
		
		try{
			CellHssf = ExcelWSheetHssf.getRow(RowNum).getCell(ColNum);
			switch (CellHssf.getCellType()) {
				case Cell.CELL_TYPE_STRING:
					CellData = CellHssf.getStringCellValue();
					break;
				case Cell.CELL_TYPE_BLANK:
					CellData = "";
					break;
				case Cell.CELL_TYPE_NUMERIC:
					if(DateUtil.isCellDateFormatted(CellHssf)) {
						CellData = CellHssf.getDateCellValue() + "";
					} else {
						Double d = CellHssf.getNumericCellValue();
						Integer dtoi = d.intValue();
						Double result = d - dtoi;
						if (result > 0){
							CellData = Double.toString(d);							
						} else {
							CellData = Integer.toString(dtoi);
						}
					}
					break;
				case Cell.CELL_TYPE_BOOLEAN:
					Boolean b = CellHssf.getBooleanCellValue();
					CellData = b.toString() + "";
					break;
				default:
					CellData = "";
			}			
			return CellData;			
		}catch (Exception e) {
			System.out.println(e.getMessage());
			throw (e);
		}
	}

	public static void createExcelXmls (String[][] reportArray, String FilePath) throws IOException {
	    
	    int totalRows = reportArray[0].length;
	    int totalColumn = reportArray.length; 
	    
		ExcelWBookXssf  = new XSSFWorkbook();
		ExcelWSheetXssf = ExcelWBookXssf.createSheet("Test report");
  
	    for (int i=0; i<totalRows; i++) {
	        XSSFRow row = ExcelWSheetXssf.createRow(i);
	        for (int j=0; j<totalColumn; j++) {
	        	 XSSFCell cell = row.createCell(j);
	        	 cell.setCellValue((String) reportArray[i][j]);	        	
	        }
	    }	     	     
	    try (FileOutputStream outputStream = new FileOutputStream(FilePath)) {
	    	ExcelWBookXssf.write(outputStream);
	    }
	}
	
	public static void addExcelRow (Integer rowNumber, String rowElements[], String FilePath) throws IOException {

	    int totalColumn = rowElements.length;
	    
		if(!new File(FilePath).isFile()){
			ExcelWBookXssf  = new XSSFWorkbook();
			ExcelWSheetXssf = ExcelWBookXssf.createSheet("Test report");
		} else {	
			   FileInputStream ExcelFile = new FileInputStream(FilePath);				
			   ExcelWBookXssf = new XSSFWorkbook(ExcelFile);
			   ExcelWSheetXssf = ExcelWBookXssf.getSheet("Test report");			
		}
		
        XSSFRow row = ExcelWSheetXssf.createRow(rowNumber);
        for (int j=0; j<totalColumn; j++) {
        	 XSSFCell cell = row.createCell(j);
        	 cell.setCellValue((String) rowElements[j]);	        	
        }	     
		try (FileOutputStream outputStream = new FileOutputStream(FilePath)) {
			ExcelWBookXssf.write(outputStream);
		}
	}
	

	
	public static void addExcelRowToEnd (String rowElements[], String FilePath) throws IOException {

	    int totalColumn = rowElements.length;
	    Integer rowNumber = 0;
	    
		if(!new File(FilePath).isFile()){
			ExcelWBookXssf  = new XSSFWorkbook();
			ExcelWSheetXssf = ExcelWBookXssf.createSheet("Test report");
		} else {	
		   FileInputStream ExcelFile = new FileInputStream(FilePath);				
		   ExcelWBookXssf = new XSSFWorkbook(ExcelFile);
		   ExcelWSheetXssf = ExcelWBookXssf.getSheet("Test report");	
		   rowNumber = ExcelWSheetXssf.getLastRowNum() + 1;
		}
		
        XSSFRow row = ExcelWSheetXssf.createRow(rowNumber);
        for (int j=0; j<totalColumn; j++) {
        	 XSSFCell cell = row.createCell(j);
        	 cell.setCellValue((String) rowElements[j]);	        	
        }	     
		try (FileOutputStream outputStream = new FileOutputStream(FilePath)) {
			ExcelWBookXssf.write(outputStream);
		}
	}

}

