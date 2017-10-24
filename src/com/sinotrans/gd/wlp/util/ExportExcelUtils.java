package com.sinotrans.gd.wlp.util;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;

import com.sinotrans.framework.core.util.ContextUtils;
import com.sinotrans.framework.core.util.NumberUtils;
import com.sinotrans.gd.wlp.common.service.SQLQueryManager;

public class ExportExcelUtils {

	HSSFWorkbook workBook;

	private boolean isNull(String inString) {
		return StringUtils.isBlank(inString);
	}

	protected final static Log log = LogFactory.getLog(ExportExcelUtils.class);

	@SuppressWarnings({ "deprecation", "null" })
	public HSSFWorkbook getExcelWorkBook(String inSQL[], String sheetName[],
			String titleArray[][]) {// ,int resultColNum[],String
		// columnDataType[]){
		// 产生工作簿对象
		workBook = new HSSFWorkbook();
		HSSFCellStyle cellStyle = workBook.createCellStyle();
		// HSSFDataFormat hssfDataFormat = new HSSFDataFormat(new Workbook());
		HSSFDataFormat hssfDataFormat = null;
		cellStyle.setDataFormat(hssfDataFormat.getFormat("yyyy-MM-dd"));

		/*
		 * HSSFFont cf = workbook.createFont(); //基本字体 HSSFCellStyle cs
		 * =workbook.createCellStyle(); //单元格样式
		 * cf.setFontHeightInPoints((short)12); //字体大小
		 * cf.setColor(HSSFFont.COLOR_RED); //字体颜色 注意使用HSSFont下的颜色常量
		 * cf.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); //字体粗细 cs.setFont(cf);
		 * //设置字体样式 cs.setAlignment(HSSFCellStyle.ALIGN_RIGHT); //对齐方式
		 */

		// int b[][]={{1,5,2,8},{5,9,10,-3},{2,7,-5,-18,10,12}};

		if (sheetName != null && sheetName.length > 0) {

			SQLQueryManager sqlQueryManager = ContextUtils
					.getBeanOfType(SQLQueryManager.class);

			for (int i = 0; i < sheetName.length; i++) {
				// 产生工作表对象
				HSSFSheet sheet = workBook.createSheet();

				// 设置第一个工作表的名称
				if (isNull(sheetName[i]))
					sheetName[i] = "导出结果-" + i;
				workBook.setSheetName(i, sheetName[i]);
				int columnNum = 0;
				HSSFRow row;
				if (titleArray != null && titleArray.length > 0) {
					// 产生一行抬头
					row = sheet.createRow((short) 0);
					String sheetTitleArray[] = titleArray[i];
					for (int j = 0; j < sheetTitleArray.length; j++) {
						if (sheetTitleArray[j] == null)
							sheetTitleArray[j] = "<" + (j + 1) + ">";
						// if (isNull(rowTitleArray[j])) continue;
						row.createCell((short) columnNum).setCellValue(
								new HSSFRichTextString(sheetTitleArray[j]));
						columnNum++;
					}
				}

				if (inSQL != null && inSQL.length > i && !isNull(inSQL[i])) {
					log.debug("inSQL[" + i + "] = " + inSQL[i]);
					List<Object[]> queryResult = sqlQueryManager
							.getSqlResultList(inSQL[i], "");

					if (queryResult != null && queryResult.size() > 0) {
						Object[] resultRow = null;

						log.debug("◆◆◆queryResult.size() = "
								+ queryResult.size());

						for (int j = 0; j < queryResult.size(); j++) {
							resultRow = queryResult.get(j);
							String columnClass;

							row = sheet.createRow((short) j + 1);

							if (resultRow.length > 0) {

								log.debug("◆◆◆resultRow.length = "
										+ resultRow.length);

								for (int col = 0; col < resultRow.length; col++) {
									// if (resultColNum[col]>=0 &&
									// resultColNum[col]< resultRow.length){
									if (resultRow[col] != null) {
										columnClass = ""
												+ resultRow[col].getClass();
										log.debug("k = " + col + " : "
												+ columnClass);

										if (columnClass
												.equals("class java.math.BigDecimal")) {
											double desValue = NumberUtils
													.parseDouble(""
															+ resultRow[col]);
											row.createCell((short) col)
													.setCellValue(desValue);
										} else if (columnClass
												.equals("class java.sql.Timestamp")) {
											Date dateValue = (Date) resultRow[col];
											// log.debug(" dateValue 1 ="+dateValue);
											row.createCell((short) col)
													.setCellValue(dateValue);
										} else if (columnClass
												.equals("class java.util.Date")) {
											Date dateValue = (Date) resultRow[col];
											row.createCell((short) col)
													.setCellValue(dateValue);
											// log.debug(" dateValue 2 ="+dateValue);
										} else {
											// columnClass.equals("class java.lang.String")
											String stringValue = (String) resultRow[col];
											row
													.createCell((short) col)
													.setCellValue(
															new HSSFRichTextString(
																	stringValue));
										}
									} else {
										log.debug("col = " + col + " : null");
									}
									// }else{
									// log.debug("resultColNum[col] overflow ");
									// }
								}
							} else {
								log.debug("row.length = 0 ");
							}
						}
					} else {
						log.debug("result = null ");
					}

				}
			}
		}
		return workBook;
	}
	
	/**
	 * 根据Cell的二维数组创建Excel文件，只支持创建单一sheet的excel文件
	 * @param sheetList
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static HSSFWorkbook getExcelWorkBook(List<List<Cell>> sheetData) {
		
		//创建工作空间及工作表
		HSSFWorkbook workBook = new HSSFWorkbook();
		HSSFSheet sheet = workBook.createSheet();
		workBook.setSheetName(0, "sheet 1");
		
		HSSFRow row;
		HSSFCell cell;
		short rowNum = 0, columnNum;
		for(List<Cell> rowData : sheetData) {
			row = sheet.createRow(rowNum);
			
			columnNum = 0;
			for(Cell cellData : rowData) {
				if (cellData == null) {
					//row.createCell(columnNum).setCellValue("");
				} else {
					int cellType = cellData.getCellType();
					
					cell = row.createCell(columnNum);
					_copyCell(workBook, cellData, cell, true);
				}
				columnNum++;
			}
			rowNum++;
		}
		
		return workBook;
	}
	
	public static void _copyCell(HSSFWorkbook wb, Cell srcCell, HSSFCell distCell, boolean copyValueFlag) {  
        HSSFCellStyle newstyle = wb.createCellStyle();

        _copyCellStyle(wb, srcCell.getCellStyle(), newstyle);
        
        //样式  
        distCell.setCellStyle(newstyle);  
        //评论  
        if (srcCell.getCellComment() != null) {  
            distCell.setCellComment(srcCell.getCellComment());  
        }  
        // 不同数据类型处理  
        int srcCellType = srcCell.getCellType();  
        distCell.setCellType(srcCellType);  
        if (copyValueFlag) {  
            if (srcCellType == HSSFCell.CELL_TYPE_NUMERIC) {  
                distCell.setCellValue(srcCell.getNumericCellValue());  
            } else if (srcCellType == HSSFCell.CELL_TYPE_STRING) {  
            	try{
            		distCell.setCellValue(srcCell.getRichStringCellValue());  
            	}catch(ClassCastException e){
            		distCell.setCellValue(srcCell.getStringCellValue());
            	}
            } else if (srcCellType == HSSFCell.CELL_TYPE_BLANK) {  
                // nothing21  
            } else if (srcCellType == HSSFCell.CELL_TYPE_BOOLEAN) {  
                distCell.setCellValue(srcCell.getBooleanCellValue());  
            } else if (srcCellType == HSSFCell.CELL_TYPE_ERROR) {  
                distCell.setCellErrorValue(srcCell.getErrorCellValue());  
            } else if (srcCellType == HSSFCell.CELL_TYPE_FORMULA) {  
                distCell.setCellFormula(srcCell.getCellFormula());  
            } else { // nothing29  
            }  
        }  
    }  
	
	private static void _copyCellStyle(HSSFWorkbook wb, CellStyle fromStyle, HSSFCellStyle toStyle) { 
        toStyle.setAlignment(fromStyle.getAlignment());  
        //边框和边框颜色  
        toStyle.setBorderBottom(fromStyle.getBorderBottom());  
        toStyle.setBorderLeft(fromStyle.getBorderLeft());  
        toStyle.setBorderRight(fromStyle.getBorderRight());  
        toStyle.setBorderTop(fromStyle.getBorderTop());  
        toStyle.setTopBorderColor(fromStyle.getTopBorderColor());  
        toStyle.setBottomBorderColor(fromStyle.getBottomBorderColor());  
        toStyle.setRightBorderColor(fromStyle.getRightBorderColor());  
        toStyle.setLeftBorderColor(fromStyle.getLeftBorderColor());  
          
        //背景和前景  
        toStyle.setFillBackgroundColor(fromStyle.getFillBackgroundColor());  
        toStyle.setFillForegroundColor(fromStyle.getFillForegroundColor());  
          
        toStyle.setDataFormat(fromStyle.getDataFormat());  
        toStyle.setFillPattern(fromStyle.getFillPattern());  
        toStyle.setHidden(fromStyle.getHidden());  
        toStyle.setIndention(fromStyle.getIndention());//首行缩进  
        toStyle.setLocked(fromStyle.getLocked());  
        toStyle.setRotation(fromStyle.getRotation());//旋转  
        toStyle.setVerticalAlignment(fromStyle.getVerticalAlignment());  
        toStyle.setWrapText(fromStyle.getWrapText());
        
    }  
	
	public static String readCell(Cell c){
		  StringBuffer b=new StringBuffer();
		  if (c==null) return "";
		  if(Cell.CELL_TYPE_STRING == c.getCellType()){
			  b.append(StringNullString(c.getStringCellValue()));
		  }else if (Cell.CELL_TYPE_NUMERIC== c.getCellType()){
			  if(c.getColumnIndex() == 9){
				  b.append(c.getNumericCellValue());
			  }else{
				  b.append(conversion(c.getNumericCellValue()));
			  }
		  } 
		  
		  return b.toString().trim();
	}
	
	private static String StringNullString(String str){	  
		 return str==null?"":str;
	}
	
	private static String conversion(double d){
		 DecimalFormat df = new DecimalFormat("###0");//最多保留几位小数，就用几个#，最少位就用0来确定  
		 String s=df.format(d);
		 return s;
	}
}
