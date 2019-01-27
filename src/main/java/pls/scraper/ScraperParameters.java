package pls.scraper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Container for external parameters coming from the command line
 */
@Component
public class ScraperParameters {

    //in a bigger application these would be in separate classes, but we've only got two fields here, so...

    @Value("${url:https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html}")
    private String targetURL;

    //TODO: handle fractional vat percentages
    @Value("${vat:20}")
    private int vat;

    public String getTargetURL() {
        return targetURL;
    }

    public int getVat() {
        return vat;
    }
}
