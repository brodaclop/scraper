package pls.scraper.output;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public interface ShopItemMixin {

    String getTitle();

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("kcal_per_100g")
    int getCalories();

    @JsonProperty("unit_price")
    @JsonSerialize(converter = PriceConverter.class)
    int getPriceInPence();

    String getDescription();

}
