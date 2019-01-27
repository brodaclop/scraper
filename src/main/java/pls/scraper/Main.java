package pls.scraper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pls.scraper.beans.ShopItem;
import pls.scraper.business.OutputRecordCalculator;
import pls.scraper.input.ShopItemLister;
import pls.scraper.input.ShopItemRetriever;
import pls.scraper.output.OutputRecord;
import pls.scraper.output.RecordOutputter;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Component
@Profile("!commandLineDisabled")
public class Main implements CommandLineRunner {

    @Autowired
    private RecordOutputter recordOutputter;
    @Autowired
    private OutputRecordCalculator outputRecordCalculator;
    @Autowired
    private ShopItemLister shopItemLister;
    @Autowired
    private ShopItemRetriever shopItemRetriever;

    @Autowired
    private Logger log;

    private ShopItem retrieve(String url) {
        try {
            return shopItemRetriever.retrieve(url);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private List<ShopItem> fetchItems() throws ScraperException {
        try {
            return shopItemLister.listItems().stream().map(this::retrieve).collect(Collectors.toList());
        } catch (IOException e) {
            throw new ScraperException("Failed to retrieve data from website", e);
        }
    }

    private void output(OutputRecord outputRecord) throws ScraperException {
        try {
            recordOutputter.output(outputRecord, System.out); // System.out could also be injected, but we're writing a simple tool here
        } catch (IOException e) {
            throw new ScraperException("Failed to write output", e);
        }
    }

    @Override
    public void run(String... args) {

        try {
            List<ShopItem> items = fetchItems();
            OutputRecord outputRecord = outputRecordCalculator.create(items);
            output(outputRecord);
        } catch (ScraperException ex) {
            System.err.println(ex.getMessage());
            log.log(Level.FINE, "Scraping failed", ex);
        }


    }
}

