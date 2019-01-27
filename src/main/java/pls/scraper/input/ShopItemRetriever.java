package pls.scraper.input;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pls.scraper.beans.ShopItem;

import java.io.IOException;

@Component
public class ShopItemRetriever {

    public static final String ITEM_TITLE_SELECTOR = "div.productTitleDescriptionContainer > h1";
    public static final String ITEM_DESCRIPTION_SELECTOR = "div.productText p";
    public static final String ITEM_UNIT_PRICE_SELECTOR = "p.pricePerUnit";
    //TODO: find a more robust way to parse calories
    public static final String ITEM_CALORIES_SELECTOR = "table.nutritionTable tr.tableRow0 td";

    @Autowired
    private NumberValueConverter numberValueConverter;

    @Autowired
    private DocumentFetcher documentFetcher;

    public ShopItem retrieve(String shopItemUrl) throws IOException {
        Document doc = documentFetcher.get(shopItemUrl);

        String title = doc.selectFirst(ITEM_TITLE_SELECTOR).text();
        String description = doc.selectFirst(ITEM_DESCRIPTION_SELECTOR).text();
        String pricePerUnit = doc.selectFirst(ITEM_UNIT_PRICE_SELECTOR).text();
        Element calories = doc.selectFirst(ITEM_CALORIES_SELECTOR); // calories are optional

        return new ShopItem(title, convertOptional(calories), numberValueConverter.toNumberIgnoreNonDigits(pricePerUnit), description);
    }

    private Integer convertOptional(Element calories) {
        return calories == null ? null : numberValueConverter.toNumberIgnoreNonDigits(calories.text());
    }

}
