package pls.scraper.business;


import com.google.common.collect.ImmutableList;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pls.scraper.ScraperParameters;
import pls.scraper.beans.ShopItem;
import pls.scraper.output.OutputRecord;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OutputRecordCalculatorTest {

    @InjectMocks
    private OutputRecordCalculator uut;

    @Mock
    private ScraperParameters scraperParameters;

    @Test
    public void noItems_shouldReturnZeroTotal() {
        when(scraperParameters.getVat()).thenReturn(10);
        OutputRecord result = uut.create(ImmutableList.of());
        Assert.assertEquals(0, result.getTotals().getGross());
        Assert.assertEquals(0, result.getTotals().getVat());
    }

    @Test
    public void singleItem_shouldReturnPriceAsTotal() {
        when(scraperParameters.getVat()).thenReturn(25);
        OutputRecord result = uut.create(ImmutableList.of(new ShopItem("title", 100, 100, "description")));
        Assert.assertEquals(100, result.getTotals().getGross());
        Assert.assertEquals(20, result.getTotals().getVat());
    }

    @Test
    public void multipleItems_shouldReturnSumOfPricesTotal() {
        when(scraperParameters.getVat()).thenReturn(20);
        OutputRecord result = uut.create(ImmutableList.of(new ShopItem("title", 100, 100, "description"), new ShopItem("title", 100, 50, "description")));
        Assert.assertEquals(150, result.getTotals().getGross());
        Assert.assertEquals(25, result.getTotals().getVat());
    }

    @Test
    public void zeroVatRate_shouldReturnZeroTotalVat() {
        when(scraperParameters.getVat()).thenReturn(0);
        OutputRecord result = uut.create(ImmutableList.of(new ShopItem("title", 100, 100, "description"), new ShopItem("title", 100, 50, "description")));
        Assert.assertEquals(150, result.getTotals().getGross());
        Assert.assertEquals(0, result.getTotals().getVat());
    }

}