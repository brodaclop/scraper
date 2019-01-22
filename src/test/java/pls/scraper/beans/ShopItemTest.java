package pls.scraper.beans;

import org.junit.Test;

public class ShopItemTest {

    @Test
    public void constructWithCorrectArgs_shouldSucceed() {
        ShopItem uut = new ShopItem("test title", 5, 42, "test description");
    }

    @Test(expected = NullPointerException.class)
    public void constructWithNullTitle_shouldThrowNPE() {
        ShopItem uut = new ShopItem(null, 5, 42, "test description");
    }

    @Test(expected = NullPointerException.class)
    public void constructWithNullDescription_shouldThrowNPE() {
        ShopItem uut = new ShopItem("test title", 5, 42, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructWithNegativeCalories_shouldThrowIAE() {
        ShopItem uut = new ShopItem("test title", -5, 42, "test description");
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructWithNegativePrice_shouldThrowIAE() {
        ShopItem uut = new ShopItem("test title", 5, -42, "test description");
    }
}
