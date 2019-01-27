package pls.scraper.input;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pls.scraper.ScraperParameters;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ShopItemLister {

    public static final String ITEM_SELECTOR = "div.productNameAndPromotions > h3 > a";
    public static final String HREF_ATTR = "href";

    @Autowired
    private ScraperParameters scraperParameters;

    @Autowired
    private DocumentFetcher documentFetcher;

    public List<String> listItems() throws IOException {
        Document doc = documentFetcher.get(scraperParameters.getTargetURL());
        Elements itemLinks = doc.select(ITEM_SELECTOR);

        return itemLinks.stream().map(i -> i.absUrl(HREF_ATTR)).collect(Collectors.toList());
    }

}
