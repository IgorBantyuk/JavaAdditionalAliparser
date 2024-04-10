package edu.ntu.dp.sa;
import edu.ntu.dp.sa.model.Item;
import edu.ntu.dp.sa.services.AliParser;
import edu.ntu.dp.sa.services.ExcelFileManager;

import java.util.List;
import java.lang.System;

public class Main {
    public static void main(String[] args) {
        AliParser aliParser = new AliParser();
        ExcelFileManager excelFileManager = new ExcelFileManager();
        List<Item> items = aliParser.parseByQuery("nike shoes",10);

        excelFileManager.saveItemsToFile("nike_shoes_report.xlsx",items,"nike shoes");
        items.forEach(it -> {
            System.out.println(it.toString());
        });
    }
}
