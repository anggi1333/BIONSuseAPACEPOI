package com.example;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

public class ExcelReader {
    private String filePath;
    private Workbook workbook;
    private Sheet sheet;

    public ExcelReader(String filePath, String sheetName) {
        this.filePath = filePath;
        try {
            FileInputStream excelFile = new FileInputStream(filePath);
            this.workbook = new XSSFWorkbook(excelFile);
            this.sheet = workbook.getSheet(sheetName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Iterator<Row> getRowIterator() {
        return sheet.iterator();
    }

    public void updateResult(int rowIndex, String actualResult) {
        Row row = sheet.getRow(rowIndex-1);
        Cell cell = row.createCell(4);
        cell.setCellValue(actualResult);
    }

    public void updateStatus(int rowIndex, String status) {
        Row row = sheet.getRow(rowIndex-1);
        Cell cell = row.createCell(5);
        cell.setCellValue(status);
    }

    public void addSummaryRow(int totalPass, int totalFailed) {
        int rowIndex = sheet.getLastRowNum() + 1; // Add new row after the last row

        Row summaryRow = sheet.createRow(rowIndex);

        // Create cell to store total PASS count
        Cell passCell = summaryRow.createCell(4);
        passCell.setCellValue("Total PASS: " + totalPass);

        // Create cell to store total FAILED count
        Cell failedCell = summaryRow.createCell(5);
        failedCell.setCellValue("Total FAILED: " + totalFailed);

        // Create style for cells
        CellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);

        // Apply style to cells
        passCell.setCellStyle(style);
        failedCell.setCellStyle(style);
    }

    public void saveChanges() {
        try {
            FileOutputStream outputStream = new FileOutputStream(filePath);
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
