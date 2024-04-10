package edu.ntu.dp.sa.services;
import edu.ntu.dp.sa.model.Item;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelFileManager {

    public static final String ALIEXPRESS_PARSING_HEADER = "AliExpress Parsing";

    public void saveItemsToFile(String fileName, List<Item> listForSave, String name){
        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet(ALIEXPRESS_PARSING_HEADER + name);
        prepareHeaderOfSheet(name, sheet);

        for(int rowNumber=2;rowNumber < listForSave.size()+2;rowNumber++){
            saveItem(listForSave, rowNumber, sheet);

        }
        try {
            FileOutputStream outputStream = new FileOutputStream(fileName);
            wb.write(outputStream);
            wb.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveItem(List<Item> listForSave, int rowNumber, Sheet sheet) {
        Item item = listForSave.get(rowNumber - 2);
        Row row = sheet.createRow(rowNumber);
        row.createCell(0).setCellValue(item.getId());
        row.createCell(1).setCellValue(item.getName());
        row.createCell(2).setCellValue(item.getPrice());
        row.createCell(3).setCellValue(item.getCurrency());
    }

    private void prepareHeaderOfSheet(String name, Sheet sheet) {
        Row rowTitle = sheet.createRow(0);
        Row rowAgenga = sheet.createRow(1);
        rowTitle.createCell(0).setCellValue("AliExpress parsing"+ name);
        rowAgenga.createCell(1).setCellValue("ID");
        rowAgenga.createCell(2).setCellValue("NAME");
        rowAgenga.createCell(3).setCellValue("PRICE");
        rowAgenga.createCell(3).setCellValue("CURRENCY");
    }
}

