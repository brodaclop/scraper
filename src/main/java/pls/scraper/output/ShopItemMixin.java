package pls.scraper.output;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public interface ShopItemMixin {

    String getTitle();

    @JsonProperty("kcal_per_100g")
    int getCalories();

    @JsonProperty("unit_price")
    @JsonSerialize(converter = PriceConverter.class)
    int getPriceInPence();

    String getDescription();

}
