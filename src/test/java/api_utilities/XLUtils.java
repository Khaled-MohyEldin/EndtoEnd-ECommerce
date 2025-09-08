package api_utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

//import org.apache.poi.ss.examples.CellStyleDetails;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtils {
    public static FileInputStream fi;
    public static FileOutputStream fo;
    public static Workbook wb;
    public static Sheet ws;
    public static Row row;
    public static Cell cell;
    public static CellStyle style;

    public static int getRowCount(String xlfile, String xlsheet) throws IOException {
        fi = new FileInputStream(xlfile);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(xlsheet);
        int rowcount = ws.getLastRowNum();
        wb.close();
        fi.close();
        return rowcount;
    }


    public static int getCellCount(String xlfile, String xlsheet, int rownum) throws IOException {
        fi = new FileInputStream(xlfile);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(xlsheet);
        row = ws.getRow(rownum);
        int cellcount = row.getLastCellNum();
        wb.close();
        fi.close();
        return cellcount;
    }

    public static String getCellData(String filePath, String sheetName, int rowNum, int colNum) throws IOException {
        /*fi = new FileInputStream(xlfile);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(xlsheet);
        row = ws.getRow(rownum);
        cell = row.getCell(colnum);
        String data;
        try {
            data = cell.getStringCellValue();
        } catch (Exception e) {
            double numericValue = cell.getNumericCellValue();
            data = String.valueOf((long) numericValue);
        }
        wb.close();
        fi.close();
        return data;*/
        String cellData = "";

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            Row row = sheet.getRow(rowNum);
            Cell cell = (row != null) ? row.getCell(colNum) : null;

            if (cell == null || cell.getCellType() == CellType.BLANK) {
                return ""; // Return empty string for blank or null cells
            }

            // A robust way to handle different cell types
            switch (cell.getCellType()) {
                case STRING:
                    cellData = cell.getStringCellValue();
                    break;
                case NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        cellData = String.valueOf(cell.getDateCellValue());
                    } else {
                        // Convert numeric value to a string
                        double numericValue = cell.getNumericCellValue();
                        cellData = String.valueOf((long) numericValue);
                    }
                    break;
                case BOOLEAN:
                    cellData = String.valueOf(cell.getBooleanCellValue());
                    break;
                case FORMULA:
                    // Evaluate the formula and get the result
                    FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
                    CellValue cellValue = evaluator.evaluate(cell);
                    if (cellValue.getCellType() == CellType.NUMERIC) {
                        cellData = String.valueOf((long) cellValue.getNumberValue());
                    } else {
                        cellData = cellValue.getStringValue();
                    }
                    break;
                default:
                    // Handle other types or return empty string
                    cellData = "";
            }
        }
        return cellData;

    }

    public static void setCellData(String xlfile, String xlsheet, int rownum, int colnum, String data) throws IOException {
        fi = new FileInputStream(xlfile);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(xlsheet);
        row = ws.getRow(rownum);
        cell = row.createCell(colnum);
        cell.setCellValue(data);
        fo = new FileOutputStream(xlfile);
        wb.write(fo);
        wb.close();
        fi.close();
        fo.close();
    }

    public static void fillGreenColor(String xlfile, String xlsheet, int rownum, int colnum) throws IOException {
        fi = new FileInputStream(xlfile);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(xlsheet);
        row = ws.getRow(rownum);
        cell = row.getCell(colnum);

        style = wb.createCellStyle();
        style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
//        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        cell.setCellStyle(style);
        fo = new FileOutputStream(xlfile);
        wb.write(fo);
        wb.close();
        fi.close();
        fo.close();
    }

    public static void fillRedColor(String xlfile, String xlsheet, int rownum, int colnum) throws IOException {
        fi = new FileInputStream(xlfile);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(xlsheet);
        row = ws.getRow(rownum);
        cell = row.getCell(colnum);

        style = wb.createCellStyle();
        style.setFillForegroundColor(IndexedColors.RED.getIndex());
//        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        cell.setCellStyle(style);
        fo = new FileOutputStream(xlfile);
        wb.write(fo);
        wb.close();
        fi.close();
        fo.close();
    }


}

