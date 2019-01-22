package pls.scraper.beans;

import com.google.common.base.Preconditions;

public final class ShopItem {
    private final String title;
    private final int calories;
    private final int priceInPence;
    private final String description;

    public ShopItem(String title, int calories, int priceInPence, String description) {
        Preconditions.checkNotNull(title, "title mustn't be null");
        Preconditions.checkNotNull(description, "description mustn't be null");
        Preconditions.checkArgument(calories >= 0, "calories can't be negative");
        Preconditions.checkArgument(priceInPence >= 0, "price can't be negative");
        this.title = title;
        this.calories = calories;
        this.priceInPence = priceInPence;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public int getCalories() {
        return calories;
    }

    public int getPriceInPence() {
        return priceInPence;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "ShopItem{" +
                "title='" + title + '\'' +
                ", calories=" + calories +
                ", priceInPence=" + priceInPence +
                ", description='" + description + '\'' +
                '}';
    }
}
