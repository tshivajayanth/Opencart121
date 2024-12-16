package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

    public FileInputStream fi;
    public FileOutputStream fo;
    public XSSFWorkbook workbook;
    public XSSFSheet sheet;
    public XSSFRow row;
    public XSSFCell cell;
    public CellStyle style;
    String path;

    // Constructor to set the file path
    public ExcelUtility(String path) {
        this.path = path;
    }

    // Get the row count of a sheet
    public int getRowCount(String sheetName) throws IOException {
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);
        int rowcount = sheet.getLastRowNum();
        workbook.close();
        fi.close();
        return rowcount;
    }

    // Get the cell count in a row
    public int getCellCount(String sheetName, int rownum) throws IOException {
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rownum);
        int cellcount = row.getLastCellNum();
        workbook.close();
        fi.close();
        return cellcount;
    }

    // Get cell data as a String
    public String getCellData(String sheetName, int rownum, int colnum) throws IOException {
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rownum); // Corrected `row-sheet.getRow(rownum);`
        cell = row.getCell(colnum);
        DataFormatter formatter = new DataFormatter();
        String data;
        try {
            data = formatter.formatCellValue(cell); // Returns formatted value as a String
        } catch (Exception e) {
            data = "";
        }
        workbook.close();
        fi.close();
        return data;
    }

    // Set cell data
    public void setCellData(String sheetName, int rownum, int colnum, String data) throws IOException {
        File xlfile = new File(path);
        if (!xlfile.exists()) {
            // Create new file if it doesn't exist
            workbook = new XSSFWorkbook();
            fo = new FileOutputStream(path);
            workbook.write(fo);
            fo.close(); // Close FileOutputStream after writing
        }

        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);

        // Check and create sheet if it doesn't exist
        if (workbook.getSheetIndex(sheetName) == -1) {
            workbook.createSheet(sheetName);
        }
        sheet = workbook.getSheet(sheetName);

        // Check and create row if it doesn't exist
        if (sheet.getRow(rownum) == null) {
            sheet.createRow(rownum);
        }

        row = sheet.getRow(rownum);
        cell = row.createCell(colnum);
        cell.setCellValue(data);

        fo = new FileOutputStream(path);
        workbook.write(fo);
        workbook.close();
        fi.close();
        fo.close();
    }

    // Fill cell with green color
    public void fillGreenColor(String sheetName, int rownum, int colnum) throws IOException {
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rownum);
        cell = row.getCell(colnum);
        style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cell.setCellStyle(style);

        fo = new FileOutputStream(path); // Fixed missing FileOutputStream initialization
        workbook.write(fo);
        workbook.close();
        fi.close();
        fo.close();
    }

    // Fill cell with red color
    public void fillRedColor(String sheetName, int rownum, int colnum) throws IOException {
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rownum);
        cell = row.getCell(colnum);

        style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.RED.getIndex()); // Corrected method name
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND); // Fixed syntax

        cell.setCellStyle(style);

        fo = new FileOutputStream(path); // Fixed missing FileOutputStream initialization
        workbook.write(fo);
        workbook.close();
        fi.close();
        fo.close();
    }
}
