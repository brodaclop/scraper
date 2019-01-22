package pls.scraper.output;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import pls.scraper.beans.ShopItem;

import java.util.List;

public final class OutputRecord {

    private final List<ShopItem> items;
    private final int grossInPence;
    private final int vatInPence;

    public OutputRecord(List<ShopItem> items, int grossInPence, int vatInPence) {
        Preconditions.checkNotNull(items, "item list can't be null");
        Preconditions.checkArgument(grossInPence >= 0, "gross total can't be negative");
        Preconditions.checkArgument(vatInPence >= 0, "vat total can't be negative");
        this.items = ImmutableList.copyOf(items);
        this.grossInPence = grossInPence;
        this.vatInPence = vatInPence;
    }

    @JsonProperty("results")
    public List<ShopItem> getItems() {
        return items;
    }

    @JsonProperty("totals")
    public Totals getTotals() {
        return new Totals(grossInPence, vatInPence);
    }

    public final static class Totals {
        private final int gross;
        private final int vat;

        private Totals(int gross, int vat) {
            this.gross = gross;
            this.vat = vat;
        }

        @JsonSerialize(converter = PriceConverter.class)
        public int getGross() {
            return gross;
        }

        @JsonSerialize(converter = PriceConverter.class)
        public int getVat() {
            return vat;
        }
    }

}
