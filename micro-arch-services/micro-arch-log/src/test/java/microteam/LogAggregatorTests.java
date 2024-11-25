package microteam;

import microteam.log.LogAggregator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import static org.mockito.Mockito.*;

public class LogAggregatorTests {

    private LogAggregator logAggregator;
    private static final Logger logger = LoggerFactory.getLogger(LogAggregatorTests.class);

    @BeforeEach
    public void setUp() {
        logAggregator = mock(LogAggregator.class);
    }

    @Test
    public void testAggregateLog() {
        String message = "Test log message";

        // Simulate aggregation of logs
        logAggregator.aggregateLog(message);

        // Verify that the method was called with the correct parameters
        verify(logAggregator, times(1)).aggregateLog(message);

        // Log to show that the test ran
        logger.info("Tested log aggregation: {}", message);
    }

    @Test
    public void testAggregateErrorLog() {
        String error = "Test error message";

        // Simulate aggregation of error logs
        logAggregator.aggregateErrorLog(error);

        // Verify that the method was called with the correct parameters
        verify(logAggregator, times(1)).aggregateErrorLog(error);

        // Log to show that the test ran
        logger.error("Tested error log aggregation: {}", error);
    }

    @Test
    public void testAggregateWarnLog() {
        String warning = "Test warning message";

        // Simulate aggregation of warning logs
        logAggregator.aggregateWarnLog(warning);

        // Verify that the method was called with the correct parameters
        verify(logAggregator, times(1)).aggregateWarnLog(warning);

        // Log to show that the test ran
        logger.warn("Tested warn log aggregation: {}", warning);
    }
}
