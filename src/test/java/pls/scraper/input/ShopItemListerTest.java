package pls.scraper.input;

import com.google.common.collect.ImmutableList;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pls.scraper.ScraperParameters;

import java.io.IOException;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ShopItemListerTest {

    @InjectMocks
    private ShopItemLister uut;

    @Mock
    private ScraperParameters scraperParameters;

    @Mock
    private DocumentFetcher documentFetcher;

    @Mock
    private Document document;

    @Mock
    private Elements elements;

    @Mock
    private Element element;

    @Test
    public void noItemsOnPage_shouldReturnEmptyList() throws IOException {
        when(documentFetcher.get(scraperParameters.getTargetURL())).thenReturn(document);
        when(document.select(ShopItemLister.ITEM_SELECTOR)).thenReturn(elements);
        when(elements.stream()).thenReturn(ImmutableList.<Element>of().stream());

        List<String> result = uut.listItems();

        Assert.assertTrue(result.isEmpty());

        verify(documentFetcher).get(scraperParameters.getTargetURL());
        verify(document).select(ShopItemLister.ITEM_SELECTOR);
        verify(elements).stream();

        verifyNoMoreInteractions(documentFetcher, document);
    }

    @Test
    public void itemOnPage_shouldReturnConvertedList() throws IOException {

        when(documentFetcher.get(scraperParameters.getTargetURL())).thenReturn(document);
        when(document.select(ShopItemLister.ITEM_SELECTOR)).thenReturn(elements);
        when(elements.stream()).thenReturn(ImmutableList.of(element).stream());
        when(element.absUrl(ShopItemLister.HREF_ATTR)).thenReturn("test_item_url");

        List<String> result = uut.listItems();

        Assert.assertEquals(ImmutableList.of("test_item_url"), result);

        verify(documentFetcher).get(scraperParameters.getTargetURL());
        verify(document).select(ShopItemLister.ITEM_SELECTOR);
        verify(elements).stream();

        verifyNoMoreInteractions(documentFetcher, document);
    }


}