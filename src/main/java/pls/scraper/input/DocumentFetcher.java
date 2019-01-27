package pls.scraper.input;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DocumentFetcher {

    public Document get(String url) throws IOException {
        return Jsoup.connect(url).get();
    }

}
