package pls.scraper.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pls.scraper.ScraperParameters;
import pls.scraper.beans.ShopItem;
import pls.scraper.output.OutputRecord;

import java.util.List;

/**
 * Calculates total gross and vat paid for a list of items
 */
@Component
public class OutputRecordCalculator {

    @Autowired
    private ScraperParameters parameters;

    public OutputRecord create(List<ShopItem> items) {
        int total = items.stream().mapToInt(ShopItem::getPriceInPence).sum();
        //TODO: make sure rounding complies with financial rules on rounding
        int net = total * 100 / (100 + parameters.getVat());
        int vat = total - net;
        return new OutputRecord(items, total, vat);
    }

}
