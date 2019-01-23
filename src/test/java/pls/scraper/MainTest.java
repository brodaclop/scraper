package pls.scraper;

import com.google.common.collect.ImmutableList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pls.scraper.business.OutputRecordCalculator;
import pls.scraper.output.OutputRecord;
import pls.scraper.output.RecordOutputter;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MainTest {

    @InjectMocks
    private Main uut;

    @Mock
    private OutputRecordCalculator outputRecordCalculator;

    @Mock
    private RecordOutputter recordOutputter;

    @Test
    public void run_shouldPassRecordToSystemOut() throws IOException {
        OutputRecord testRecord = new OutputRecord(ImmutableList.of(), 0, 0);
        when(outputRecordCalculator.create(any())).thenReturn(testRecord);
        doNothing().when(recordOutputter).output(testRecord, System.out); // note: referring directly to System.out makes this test slightly brittle

        uut.run(null);

        verify(outputRecordCalculator).create(any());
        verify(recordOutputter).output(testRecord, System.out);
        verifyNoMoreInteractions(outputRecordCalculator, recordOutputter);
    }

    @Test
    public void run_shouldCatchIOExceptionOnOutput() throws IOException {
        OutputRecord testRecord = new OutputRecord(ImmutableList.of(), 0, 0);
        when(outputRecordCalculator.create(any())).thenReturn(testRecord);
        doThrow(IOException.class).when(recordOutputter).output(testRecord, System.out);

        uut.run(null);

        //TODO: check the correct error output

    }
}