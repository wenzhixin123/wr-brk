package com.sinotrans.gd.wlp.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class ExcelFileParser {
	
	public static final String EXCEL_97 = "XLS";
	public static final String EXCEL_2007 = "XLSX";
	
	/*
	 * return List{worksheet_1<list>{row1<List>{col1,col2,...},
	 * 							     row2<List>{col1,col2,...},
	 *                               ...
	 *                               row_n<List>{col1,col2,...}
	 *                               },
	 *             worksheet_2<list>{row1<List>{col1,col2,...},
	 * 							     row2<List>{col1,col2,...},
	 *                               ...
	 *                               row_n<List>{col1,col2,...}
	 *                               },
	 *             ...
	 *             worksheet_n<list>{row1<List>{col1,col2,...},
	 * 							     row2<List>{col1,col2,...},
	 *                               ...
	 *                               row_n<List>{col1,col2,...}
	 *                               }   
	 *             }
	 */
	public List<List<List<Cell>>> parseExcel(File file, String type) throws Exception{
		List<List<List<Cell>>> sheetList = new ArrayList<List<List<Cell>>>();
		
		if (type.equalsIgnoreCase(EXCEL_97)){
			HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(file));
			for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++){
				HSSFSheet sheet = workbook.getSheetAt(sheetIndex);
				List<List<Cell>> rowList = new ArrayList<List<Cell>>();
				for(int i= 0; i< sheet.getPhysicalNumberOfRows(); i++){
					List<Cell> colList = new ArrayList<Cell>();
					HSSFRow row = sheet.getRow(i);
					if(row == null) continue;
					int colNum = row.getLastCellNum();
					for(int colIndex = 0; colIndex< colNum; colIndex++){
						Cell cell = row.getCell(colIndex);
						colList.add(cell);
						//System.out.print(cell == null||cell.getCellType()==0? null: cell.toString());
					}
					
					rowList.add(colList);
				}
				sheetList.add(rowList);
			}
			workbook=null;
			
		}else if (type.equalsIgnoreCase(EXCEL_2007)){
			XSSFWorkbook workbook = new XSSFWorkbook(new BufferedInputStream(new FileInputStream(file)));
			//SXSSFWorkbook sxssfWorkbook = new SXSSFWorkbook(workbook,1000);
			for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++){
				Sheet sheet = workbook.getSheetAt(sheetIndex);
				List<List<Cell>> rowList = new ArrayList<List<Cell>>();
				for(int i= 0; i< sheet.getPhysicalNumberOfRows(); i++){
					List<Cell> colList = new ArrayList<Cell>();
					Row row = sheet.getRow(i);
					if(row == null) continue;
					int colNum = row.getLastCellNum();
					for(int colIndex = 0; colIndex< colNum; colIndex++){
						Cell cell = row.getCell(colIndex);
						colList.add(cell);
						//System.out.print(cell == null||cell.getCellType()==0? null: cell.toString());
					}
					
					rowList.add(colList);
				}
				sheetList.add(rowList);
			}
			workbook=null;
		}else{
			throw new RuntimeException("文件类型不正确");
		}
		return sheetList;
	}

	

}
