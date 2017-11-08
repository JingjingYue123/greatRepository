package com.djcps.crm.commons.utils;

import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 操作EXCEL文件
 * Created by TruthBean on 2017-04-19 14:35.
 */
public class ExcelUtil {

    // 标题样式
    private static CellStyle titleStyle = null;

    // 行信息内容样式
    private static CellStyle contentStyle = null;

    public static boolean isExcel2003(String filePath) {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    public static boolean isExcel2007(String filePath) {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }

    /**
     * 读取excel sheet1 中的数据
     *
     * @param workbook workbook
     * @return String[][]
     */
    public static String[][] readExcelSheet1(Workbook workbook) {
        Sheet sheet = workbook.getSheetAt(0);

        //Iterate through each rows one by one
        Row firstRow = sheet.getRow(0);
        int cellLength = 0;
        if (firstRow != null) {
            cellLength = firstRow.getLastCellNum();
        }

        int rowLength = cellLength > 0 ? sheet.getLastRowNum() + 1 : 0;
        String[][] data = new String[rowLength][cellLength];

        int i = 0;
        for (Row row : sheet) {
            //For each row, iterate through all the columns
            Iterator<Cell> cellIterator = row.cellIterator();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                if (cell != null) {
                    int index = cell.getColumnIndex();
                    if (index < cellLength) {
                        row.getCell(index).setCellType(CellType.STRING);
                        //Check the cell type and format accordingly
                        switch (cell.getCellTypeEnum()) {
                            case NUMERIC:
                                data[i][index] = new BigDecimal(cell.getNumericCellValue()).toPlainString();
                                break;
                            case STRING:
                                data[i][index] = cell.getStringCellValue();
                                break;
                            case BLANK:
                                data[i][index] = "";
                                break;
                        }
                    }
                } else {
                    break;
                }
            }

            i++;
        }

        return data;
    }

    /**
     * 读取excel sheet1 中的数据
     *
     * @param fileName 文件名称
     * @return String[][]
     */
    public static String[][] readExcelSheet1(InputStream inputStream, String fileName) {
        try {
            Workbook workbook = null;
            if (isExcel2003(fileName)) {
                workbook = new HSSFWorkbook(inputStream);
            } else if (isExcel2007(fileName)) {
                workbook = new XSSFWorkbook(inputStream);
            }
            return readExcelSheet1(workbook);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 读取excel sheet1 中的数据
     *
     * @param path 文件路径
     * @return String[][]
     */
    public static String[][] readExcelSheet1(String path) {
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(path));
            Workbook workbook = null;
            if (isExcel2003(path)) {
                workbook = new HSSFWorkbook(fileInputStream);
            } else if (isExcel2007(path)) {
                workbook = new XSSFWorkbook(fileInputStream);
            }
            return readExcelSheet1(workbook);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 写excel文件
     */
    public static Workbook writeExcel(Workbook wb, String[] titleStrs, List<String[]> contentList, String sheetName, boolean isExcel2003) {
        Sheet sheet;
        int titleCount = titleStrs.length;// 列数
        int begin = 0;
        // 创建新HSSFWorkbook对象
        if (wb == null) {
            if (isExcel2003) {
                wb = new HSSFWorkbook();
            } else {
                wb = new XSSFWorkbook();
            }
            //执行样式初始化
            setExcelStyle(wb);

            // 创建新的sheet对象
            sheet = wb.createSheet(sheetName);

            //创建第一行
            Row titleRow = sheet.createRow((short) 0);

            // titleRow.setHeight((short)300);//设置行高,设置太小可能被隐藏看不到
            titleRow.setHeightInPoints(20);//20像素

            // 写标题
            for (int k = 0; k < titleCount; k++) {
                Cell cell = titleRow.createCell((short) k); // 新建一个单元格

                // cell.setEncoding(HSSFCell.ENCODING_UTF_16); // 中文字符集转换
                cell.setCellStyle(titleStyle);//设置标题样式
                // cell.setCellValue(new HSSFRichTextString(titleStrs[k])); // 为单元格赋值
                // cell.setCellValue(wb.getCreationHelper().createRichTextString(""));
                cell.setCellType(CellType.STRING);
                cell.setCellValue(titleStrs[k]);
                sheet.setColumnWidth((short) k, (short) 5000);//设置列宽
                begin = sheet.getLastRowNum();
            }
        } else {
            sheet = wb.getSheet(sheetName);
        }

        int contentCount = contentList.size();//总的记录数
        // 写内容
        for (int i = begin; i < contentCount; i++) {
            String[] contents = contentList.get(i);
            Row row = sheet.createRow((short) (i + 1)); // 新建一行

            for (int j = 0; j < titleCount; j++) {
                Cell cell = row.createCell((short) j); // 新建一个单元格

                cell.setCellStyle(contentStyle);//设置内容样式
                if (contents[j] == null || contents[j].equals("null")) {
                    contents[j] = "";
                }
                //格式化日期
                if (j == 2) {
                    CellStyle style = wb.createCellStyle();
                    style.setDataFormat(wb.getCreationHelper().createDataFormat().getFormat("yyyy年mm月dd日 hh点mm分"));
                    cell.setCellValue(contents[j]);
                    cell.setCellStyle(style);
                } else {
                    if (isExcel2003) {
                        cell.setCellValue(new HSSFRichTextString(contents[j]));
                    } else {
                        cell.setCellValue(new XSSFRichTextString(contents[j]));
                    }
                }
            }
        }

        return wb;
    }

    public static void writeToFile(Workbook wb, String[] titleStrs, List<String[]> contentList, String sheetName,
                                   String distFile) {
        try {
            boolean isExcel2003;
            if (isExcel2003(distFile)) {
                isExcel2003 = true;
            } else if (isExcel2007(distFile)) {
                isExcel2003 = false;
            } else {
                throw new RuntimeException("this file is not a excel");
            }
            Workbook workbook = writeExcel(wb, titleStrs, contentList, sheetName, isExcel2003);
            FileOutputStream fileOut = new FileOutputStream(distFile);
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeToServletResponse(HttpServletResponse response, String filename, Workbook wb) {
        //severlet 响应生成excel文件
        try {
            String head = new String(filename.getBytes("GB2312"), "ISO-8859-1");
            response.reset();
            response.setContentType("application/vnd.ms-excel");
            response.addHeader("Content-Disposition", "attachment; filename=" + head);
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 样式初始化
     */
    public static void setExcelStyle(Workbook workBook) {
        // 设置列标题字体，样式
        Font titleFont = workBook.createFont();
        titleFont.setBold(true);
        // 标题列样式
        titleStyle = workBook.createCellStyle();
        titleStyle.setBorderTop(BorderStyle.THIN); // 设置边框
        titleStyle.setBorderBottom(BorderStyle.THIN);
        titleStyle.setBorderLeft(BorderStyle.THIN);
        titleStyle.setBorderRight(BorderStyle.THIN);
        titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        titleStyle.setAlignment(HorizontalAlignment.CENTER);
        titleStyle.setFont(titleFont);
        // 内容列样式
        contentStyle = workBook.createCellStyle();
        contentStyle.setBorderTop(BorderStyle.THIN);
        contentStyle.setBorderBottom(BorderStyle.THIN);
        contentStyle.setBorderLeft(BorderStyle.THIN);
        contentStyle.setBorderRight(BorderStyle.THIN);
        contentStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        contentStyle.setAlignment(HorizontalAlignment.CENTER);
    }
}
