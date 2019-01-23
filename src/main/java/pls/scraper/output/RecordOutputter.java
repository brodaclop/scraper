package pls.scraper.output;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintStream;

@Component
public class RecordOutputter {

    @Autowired
    private ObjectMapper objectMapper;

    public void output(OutputRecord record, PrintStream out) throws IOException {
        objectMapper.writer().writeValue(out, record);
    }

}
