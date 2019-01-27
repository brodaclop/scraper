package pls.scraper.input;

import org.junit.Assert;
import org.junit.Test;


public class NumberValueConverterTest {

    private NumberValueConverter uut = new NumberValueConverter();

    @Test
    public void convertPriceWithDecimals_shouldReturnValueInPence() {
        Assert.assertEquals(1526, uut.toNumberIgnoreNonDigits("£15.26"));
    }

    @Test
    public void convertCalories_shouldReturnCalorieNumber() {
        Assert.assertEquals(32, uut.toNumberIgnoreNonDigits("32 kcal"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void convertInvalidNumber_shouldThrowsIAE() {
        uut.toNumberIgnoreNonDigits("not a number");
    }

}