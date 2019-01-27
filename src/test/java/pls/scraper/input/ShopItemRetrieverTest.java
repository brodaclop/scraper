package pls.scraper.input;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pls.scraper.beans.ShopItem;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ShopItemRetrieverTest {

    @InjectMocks
    private ShopItemRetriever uut;

    @Mock
    private DocumentFetcher documentFetcher;

    @Mock
    private Document document;

    @Mock
    private Element titleElement;

    @Mock
    private Element descriptionElement;

    @Mock
    private Element priceElement;

    @Mock
    private Element calorieslement;

    @Mock
    private NumberValueConverter numberValueConverter;

    @Test
    public void retrieveItemWithCalories_shouldReturnItemIncludingCalories() throws IOException {
        when(documentFetcher.get("item_url")).thenReturn(document);
        when(document.selectFirst(ShopItemRetriever.ITEM_TITLE_SELECTOR)).thenReturn(titleElement);
        when(document.selectFirst(ShopItemRetriever.ITEM_DESCRIPTION_SELECTOR)).thenReturn(descriptionElement);
        when(document.selectFirst(ShopItemRetriever.ITEM_CALORIES_SELECTOR)).thenReturn(calorieslement);
        when(document.selectFirst(ShopItemRetriever.ITEM_UNIT_PRICE_SELECTOR)).thenReturn(priceElement);

        when(titleElement.text()).thenReturn("title");
        when(descriptionElement.text()).thenReturn("description");
        when(calorieslement.text()).thenReturn("calories");
        when(priceElement.text()).thenReturn("price");

        when(numberValueConverter.toNumberIgnoreNonDigits("calories")).thenReturn(5);
        when(numberValueConverter.toNumberIgnoreNonDigits("price")).thenReturn(42);

        ShopItem item = uut.retrieve("item_url");

        assertEquals("title", item.getTitle());
        assertEquals("description", item.getDescription());
        assertEquals((Integer) 5, item.getCalories());
        assertEquals(42, item.getPriceInPence());
    }

    @Test
    public void retrieveItemWithoutCalories_shouldReturnItemWithNullCalories() throws IOException {
        when(documentFetcher.get("item_url")).thenReturn(document);
        when(document.selectFirst(ShopItemRetriever.ITEM_TITLE_SELECTOR)).thenReturn(titleElement);
        when(document.selectFirst(ShopItemRetriever.ITEM_DESCRIPTION_SELECTOR)).thenReturn(descriptionElement);
        when(document.selectFirst(ShopItemRetriever.ITEM_CALORIES_SELECTOR)).thenReturn(null);
        when(document.selectFirst(ShopItemRetriever.ITEM_UNIT_PRICE_SELECTOR)).thenReturn(priceElement);

        when(titleElement.text()).thenReturn("title");
        when(descriptionElement.text()).thenReturn("description");
        when(priceElement.text()).thenReturn("price");

        when(numberValueConverter.toNumberIgnoreNonDigits("price")).thenReturn(42);

        ShopItem item = uut.retrieve("item_url");

        assertEquals("title", item.getTitle());
        assertEquals("description", item.getDescription());
        assertNull(item.getCalories());
        assertEquals(42, item.getPriceInPence());
    }

    @Test(expected = IOException.class)
    public void connectionFailure_shouldThrowIOE() throws IOException {
        when(documentFetcher.get("item_url")).thenThrow(IOException.class);
        ShopItem item = uut.retrieve("item_url");
    }

    @Test(expected = IOException.class)
    public void connectionFailed_shouldThrowIOE() throws IOException {
        when(documentFetcher.get("item_url")).thenThrow(IOException.class);
        uut.retrieve("item_url");
    }

}