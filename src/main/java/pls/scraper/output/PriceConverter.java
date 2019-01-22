package pls.scraper.output;

import com.fasterxml.jackson.databind.util.StdConverter;

public class PriceConverter extends StdConverter<Integer, Double> {

    @Override
    public Double convert(Integer priceInPence) {
        return (double) priceInPence / 100;
    }


}
