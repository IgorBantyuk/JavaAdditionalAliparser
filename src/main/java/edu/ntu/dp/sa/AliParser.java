package edu.ntu.dp.sa;
import edu.ntu.dp.sa.model.Item;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AliParser {

    public static final String ALIEXPRESS_HEADER_URL = "http://www.aliexpress.com";
    public static final String ALIEXPRESS_CATALOG_POSTFIX = "/w/wholesale-";
    public static final String ALIEXPRESS_ITEM_POSTFIX = "item/";
    public static final String ALIEXPRESS_ALWAYS_POSTFIX = ".html";
    public static final int ALIEXPRESS_MAX_TIMEOUT = 30000;


    public List<Item> parseByQuery(String query, int maxParseCount) {
        String validQuery = validateQuery(query);

        List<Item> items = new ArrayList<>();
        Document document;
        String fullUrl = "";
        Elements elements;
        do {
            try {
                fullUrl = ALIEXPRESS_HEADER_URL + ALIEXPRESS_CATALOG_POSTFIX + validQuery + ALIEXPRESS_ALWAYS_POSTFIX;
                document = Jsoup.connect(fullUrl).timeout(ALIEXPRESS_MAX_TIMEOUT).get();
            } catch (IOException e) {
                System.out.println("problems with connetion to " + fullUrl);
                throw new RuntimeException(e);
            }
            System.out.println(ALIEXPRESS_HEADER_URL + ALIEXPRESS_CATALOG_POSTFIX + validQuery + ALIEXPRESS_ALWAYS_POSTFIX);
            elements = document.getElementsByAttributeValue("class", "list--gallery--C2f2tvm search-item-card-wrapper-gallery");


        }
        while (elements.isEmpty());
        Element element;
        for (int i = 1; i <= 3; i++) {
            element = elements.get(i);
            element.getElementsByAttributeValue("class", "multi--container--1UZxxHY cards--card--3PJxwBm search-card-item");
            String link = element.getElementsByAttributeValue("class", "multi--container--1UZxxHY cards--card--3PJxwBm search-card-item").get(0).attributes().get("href");
            String linkValid = link.split(ALIEXPRESS_ALWAYS_POSTFIX)[0];
            items.add(createItemFromLink("http:" + linkValid + ALIEXPRESS_ALWAYS_POSTFIX));
        }
        return items;
    }

    private String validateQuery(String inputQuery) {
        String validQuery;
        validQuery = inputQuery.trim();
        validQuery = validQuery.replace(" ", "-");
        return validQuery;
    }

    private Item createItemFromLink(String link) {
        System.out.println(link);

        Document document;
        try {
            document = Jsoup.connect(link).timeout(ALIEXPRESS_MAX_TIMEOUT).get();
        } catch (IOException e) {
            System.out.println("problems with connetion to" + link);
            throw new RuntimeException(e);
        }
        String scriptText = document.getElementsByTag("script").get(0).toString();
        String priceInfo = scriptText.split("\"skuActivityAmount\":")[1].split("}")[0];
        System.out.println(priceInfo);
        String price = priceInfo.split("value")[1].replace("\"", "").replace(":", "");
        String currency = priceInfo.split("currency")[1].split("\",\"")[0].replace("\"", "").replace(":", "");
        String productInfo = scriptText.split("\"productInfoComponent\":")[1].split("subject")[1].split("\",\"")[0]
                .replace("\"", "").replace(":", "");
        String id = link.replace(ALIEXPRESS_HEADER_URL + ALIEXPRESS_ITEM_POSTFIX, "").replace(ALIEXPRESS_ALWAYS_POSTFIX, "").split("item/")[1];

        return new Item(id, productInfo, Float.parseFloat(price), currency);
    }
}