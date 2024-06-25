package com.example;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

public class ExcelReader {
    private Workbook workbook;
    private Sheet sheet;
    private String filePath;

    public ExcelReader(String filePath) throws IOException {
        this.filePath = filePath;
        try (FileInputStream fis = new FileInputStream(filePath)) {
            this.workbook = new XSSFWorkbook(fis);
            this.sheet = workbook.getSheetAt(0); // Assuming the first sheet is used
        }
    }

    public Iterator<Row> getRowIterator() {
        return sheet.iterator();
    }

    public void updateResult(int rowIndex, String result) {
        Row row = sheet.getRow(rowIndex);
        if (row == null) {
            row = sheet.createRow(rowIndex);
        }
        row.createCell(3).setCellValue(result); // Assuming column 2 is for Actual Result
        save();
    }

    public void updateStatus(int rowIndex, String status) {
        Row row = sheet.getRow(rowIndex);
        if (row == null) {
            row = sheet.createRow(rowIndex);
        }
        row.createCell(4).setCellValue(status); // Assuming column 3 is for Status
        save();
    }

    public void addSummaryRow(int passCount, int failCount) {
        int lastRow = sheet.getLastRowNum() + 1;
        Row row = sheet.createRow(lastRow);
        row.createCell(0).setCellValue("Pass Count: " + passCount);
        row.createCell(1).setCellValue("Fail Count: " + failCount);
        save();
    }

    private void save() {
        try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
