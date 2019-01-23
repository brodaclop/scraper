package pls.scraper;

import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pls.scraper.beans.ShopItem;
import pls.scraper.business.OutputRecordCalculator;
import pls.scraper.output.OutputRecord;
import pls.scraper.output.RecordOutputter;

import java.io.IOException;

@Component
@Profile("!commandLineDisabled")
public class Main implements CommandLineRunner {

    @Autowired
    private RecordOutputter recordOutputter;
    @Autowired
    private OutputRecordCalculator outputRecordCalculator;

    @Override
    public void run(String... args) {

        //TODO: obtain items from scraping
        OutputRecord outputRecord = outputRecordCalculator.create(ImmutableList.of(new ShopItem("dummy item", 100, 15, "dummy description")));

        try {
            recordOutputter.output(outputRecord, System.out); // System.out could also be injected, but we're writing a simple tool here
        } catch (IOException ex) {
            //TODO: log exception on debug level
            System.err.println("Failed to write output.");
        }
    }
}

