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

    private ShopItem retrieve(String url) {
        try {
            return shopItemRetriever.retrieve(url);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    @Override
    public void run(String... args) {

        try {
            List<ShopItem> items = shopItemLister.listItems().stream().map(this::retrieve).collect(Collectors.toList());
            OutputRecord outputRecord = outputRecordCalculator.create(items);
            try {
                recordOutputter.output(outputRecord, System.out); // System.out could also be injected, but we're writing a simple tool here
            } catch (IOException ex) {
                //TODO: log exception on debug level
                System.err.println("Failed to write output.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

