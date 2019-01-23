package pls.scraper.output;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.common.collect.ImmutableList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.io.PrintStream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RecordOutputterTest {

    @InjectMocks
    private RecordOutputter uut;

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private ObjectWriter objectWriter;

    @Mock
    private PrintStream printStream;

    @Test
    public void output_shouldCallMapper() throws IOException {
        when(objectMapper.writer()).thenReturn(objectWriter);
        doNothing().when(objectWriter).writeValue(any(PrintStream.class), any());

        uut.output(new OutputRecord(ImmutableList.of(), 0, 0), printStream);

        verify(objectMapper).writer();
        verify(objectWriter).writeValue(any(PrintStream.class), any());
        verifyNoMoreInteractions(objectWriter, objectWriter);
    }
}