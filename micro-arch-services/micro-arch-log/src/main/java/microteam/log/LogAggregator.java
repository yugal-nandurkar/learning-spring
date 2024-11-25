package microteam.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LogAggregator {

    private static final Logger logger = LoggerFactory.getLogger(LogAggregator.class);

    /**
     * Method to aggregate logs from various services and output them to a central location.
     * @param message Log message to be aggregated.
     */
    public void aggregateLog(String message) {
        logger.info("Aggregated Log: " + message);
    }

    /**
     * Method to simulate error log aggregation for testing.
     * @param error Error message to be aggregated.
     */
    public void aggregateErrorLog(String error) {
        logger.error("Aggregated Error: " + error);
    }

    /**
     * Method to simulate warn log aggregation for testing.
     * @param warning Warning message to be aggregated.
     */
    public void aggregateWarnLog(String warning) {
        logger.warn("Aggregated Warning: " + warning);
    }
}

