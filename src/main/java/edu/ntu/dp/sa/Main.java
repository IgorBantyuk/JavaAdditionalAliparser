package edu.ntu.dp.sa;
import edu.ntu.dp.sa.model.Item;
import java.util.List;
import java.lang.System;

public class Main {
    public static void main(String[] args) {
        AliParser aliParser = new AliParser();
        List<Item> items = aliParser.parseByQuery("nike shoes",10);
        items.forEach(it -> {
            System.out.println(it.toString());
        });
    }
}
