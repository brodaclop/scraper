package pls.scraper.output;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pls.scraper.beans.ShopItem;

@Configuration
public class JacksonConfiguration {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper ret = new ObjectMapper();
        ret.addMixIn(ShopItem.class, ShopItemMixin.class);
        return ret;
    }

}
