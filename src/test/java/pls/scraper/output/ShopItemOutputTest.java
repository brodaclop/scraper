package pls.scraper.output;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import pls.scraper.beans.ShopItem;

import java.util.Collection;

@RunWith(Parameterized.class)
@SpringBootTest
//TODO: make test more robust by de-serialising JSON output back instead of string comparison
public class ShopItemOutputTest {

    @ClassRule
    public static final SpringClassRule springClassRule = new SpringClassRule();
    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();
    @Parameterized.Parameter(0)
    public ShopItem input;
    @Parameterized.Parameter(1)
    public String expectedOutput;
    @Autowired
    private ObjectMapper uut;

    @Parameterized.Parameters(name = "{index}: {0} => {1}")
    public static Collection<Object[]> data() {
        return ImmutableList.copyOf(new Object[][]{
                {new ShopItem("test", 5, 12, "test description"), "{\"title\":\"test\",\"description\":\"test description\",\"kcal_per_100g\":5,\"unit_price\":0.12}"},
                {new ShopItem("test", 5, 100, "test description"), "{\"title\":\"test\",\"description\":\"test description\",\"kcal_per_100g\":5,\"unit_price\":1.0}"},
                {new ShopItem("test", null, 100, "test description"), "{\"title\":\"test\",\"description\":\"test description\",\"unit_price\":1.0}"}
        });
    }

    @Test
    public void serialiseItem_shouldMatchExpected() throws JsonProcessingException {
        String result = uut.writeValueAsString(input);
        Assert.assertEquals(expectedOutput, result);
    }

}
