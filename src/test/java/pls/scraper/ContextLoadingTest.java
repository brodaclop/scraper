package pls.scraper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ActiveProfiles("commandLineDisabled")
@SpringBootTest
public class ContextLoadingTest {

    @Test
    public void contextLoads() {
    }

}

